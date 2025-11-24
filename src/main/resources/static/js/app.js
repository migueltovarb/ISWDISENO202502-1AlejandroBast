// Auth Logic
const loginForm = document.getElementById('login-form');
const registerForm = document.getElementById('register-form');
const messageDiv = document.getElementById('message');

async function apiFetch(url, options = {}) {
    const res = await fetch(url, options);
    if (res.status === 401) {
        window.location.href = '/index.html';
        throw new Error('Unauthorized');
    }
    const ct = res.headers.get('content-type') || '';
    let data;
    try {
        data = ct.includes('application/json') ? await res.json() : await res.text();
    } catch (_) {
        data = null;
    }
    return { ok: res.ok, status: res.status, data };
}

// Login Handler
if (loginForm) {
    loginForm.addEventListener('submit', async (e) => {
        e.preventDefault();
        const formData = new FormData(loginForm);
        const data = Object.fromEntries(formData.entries());

        const params = new URLSearchParams();
        params.append('username', data.username);
        params.append('password', data.password);

        try {
            const response = await apiFetch('/api/auth/login', {
                method: 'POST',
                headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
                body: params
            });
            if (response.ok) {
                window.location.href = '/dashboard.html';
            } else {
                showMessage('Login failed. Please check your credentials.', 'error');
            }
        } catch (error) {
            console.error(error);
            showMessage('An error occurred during login.', 'error');
        }
    });
}

// Register Handler
if (registerForm) {
    registerForm.addEventListener('submit', async (e) => {
        e.preventDefault();
        const formData = new FormData(registerForm);
        const data = Object.fromEntries(formData.entries());

        const regData = {
            username: data.username,
            password: data.password,
            roles: data.roles ? [data.roles] : ['ROLE_USER']
        };

        try {
            const response = await apiFetch('/api/auth/register', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(regData)
            });

            if (response.ok) {
                showMessage('Registration successful! Redirecting to login...', 'success');
                setTimeout(() => {
                    window.location.href = '/index.html';
                }, 1500);
            } else {
                showMessage('Registration failed. Username may be taken.', 'error');
            }
        } catch (error) {
            console.error(error);
            showMessage('An error occurred during registration.', 'error');
        }
    });
}

function showMessage(msg, type) {
    if (!messageDiv) return;
    messageDiv.textContent = msg;
    messageDiv.className = `alert alert-${type}`;
    messageDiv.classList.remove('hidden');
}

// Dashboard Logic
async function checkAuth() {
    try {
        const res = await apiFetch('/api/auth/current-user');
        if (!res.ok) {
            window.location.href = '/index.html';
            return;
        }
        const user = res.data;
        const greeting = document.getElementById('user-greeting');
        if (greeting) greeting.textContent = `Welcome, ${user.username}`;

        // Show admin link if admin
        if (user.roles.includes('ROLE_ADMIN') && !window.location.href.includes('admin.html')) {
            const nav = document.querySelector('.nav-links');
            const adminLink = document.createElement('a');
            adminLink.href = '/admin.html';
            adminLink.textContent = 'Admin Dashboard';
            nav.insertBefore(adminLink, nav.firstChild);
        }
    } catch (e) {
        window.location.href = '/index.html';
    }
}

async function loadRooms() {
    const grid = document.getElementById('rooms-grid');
    const empty = document.getElementById('rooms-empty');
    grid.innerHTML = '<div class="card card-content">Cargando habitaciones...</div>';
    const res = await apiFetch('/api/rooms');
    window.__rooms = Array.isArray(res.data) ? res.data : [];
    applyRoomFilters();
}

function applyRoomFilters() {
    const type = document.getElementById('type-filter')?.value || '';
    const sort = document.getElementById('sort-filter')?.value || '';
    const search = document.getElementById('search-input')?.value?.toLowerCase() || '';
    const grid = document.getElementById('rooms-grid');
    const empty = document.getElementById('rooms-empty');
    let rooms = (window.__rooms || []).slice();
    if (type) rooms = rooms.filter(r => r.type === type);
    if (search) rooms = rooms.filter(r => (`${r.type} ${r.number} ${r.description || ''}`).toLowerCase().includes(search));
    if (sort === 'price-asc') rooms.sort((a,b) => (a.price||0) - (b.price||0));
    if (sort === 'price-desc') rooms.sort((a,b) => (b.price||0) - (a.price||0));
    if (!rooms.length) { empty.classList.remove('hidden'); grid.innerHTML = ''; return; }
    empty.classList.add('hidden');
    grid.innerHTML = rooms.map(room => `
        <div class="card">
            <img src="${room.imageUrl || 'https://via.placeholder.com/300'}" loading="lazy" class="card-image" alt="Habitación ${room.number}">
            <div class="card-content">
                <h3>${room.type} - Habitación ${room.number}</h3>
                <p class="price">$${room.price}/noche</p>
                <p>${room.description || ''}</p>
                <button onclick="bookRoom('${room.id}')" style="margin-top: 1rem;">Reservar</button>
            </div>
        </div>
    `).join('');
}

document.getElementById('type-filter')?.addEventListener('change', applyRoomFilters);
document.getElementById('sort-filter')?.addEventListener('change', applyRoomFilters);
document.getElementById('search-btn')?.addEventListener('click', applyRoomFilters);
document.getElementById('search-input')?.addEventListener('input', applyRoomFilters);
document.getElementById('search-input')?.addEventListener('keydown', (e) => { if (e.key === 'Enter') { e.preventDefault(); applyRoomFilters(); } });

async function bookRoom(roomId) {
    const modal = document.getElementById('booking-modal');
    const form = document.getElementById('booking-form');
    const msg = document.getElementById('booking-message');
    const cancelBtn = document.getElementById('booking-cancel');
    const roomInput = document.getElementById('booking-room-id');
    roomInput.value = roomId;
    msg.className = 'alert hidden';
    modal.classList.remove('hidden');

    const onSubmit = async (e) => {
        e.preventDefault();
        const checkIn = document.getElementById('check-in').value;
        const checkOut = document.getElementById('check-out').value;
        if (!checkIn || !checkOut) {
            msg.textContent = 'Completa ambas fechas';
            msg.className = 'alert alert-error';
            return;
        }
        if (new Date(checkOut) <= new Date(checkIn)) {
            msg.textContent = 'La salida debe ser posterior al ingreso';
            msg.className = 'alert alert-error';
            return;
        }
        try {
            const res = await apiFetch('/api/reservations', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ roomId, checkInDate: checkIn, checkOutDate: checkOut })
            });
            if (res.ok) {
                msg.textContent = 'Reserva confirmada';
                msg.className = 'alert alert-success';
                setTimeout(() => {
                    modal.classList.add('hidden');
                    form.reset();
                }, 800);
            } else {
                msg.textContent = 'Error al reservar';
                msg.className = 'alert alert-error';
            }
        } catch (e) {
            msg.textContent = 'Error de red';
            msg.className = 'alert alert-error';
        }
    };
    const onCancel = () => {
        modal.classList.add('hidden');
        form.reset();
        form.removeEventListener('submit', onSubmit);
        cancelBtn.removeEventListener('click', onCancel);
    };
    form.addEventListener('submit', onSubmit);
    cancelBtn.addEventListener('click', onCancel);
}

// Logout
const logoutBtn = document.getElementById('logout-btn');
if (logoutBtn) {
    logoutBtn.addEventListener('click', async (e) => {
        e.preventDefault();
        await apiFetch('/api/auth/logout', { method: 'POST' });
        window.location.href = '/index.html';
    });
}

const myBookingsLink = document.getElementById('my-bookings-link');
if (myBookingsLink) {
    myBookingsLink.addEventListener('click', async (e) => {
        e.preventDefault();
        document.getElementById('rooms-section').classList.add('hidden');
        document.getElementById('bookings-section').classList.remove('hidden');
        const list = document.getElementById('bookings-list');
        const empty = document.getElementById('bookings-empty');
        list.innerHTML = '<div class="card card-content">Cargando reservas...</div>';
        try {
            const res = await apiFetch('/api/reservations/my');
            const bookings = Array.isArray(res.data) ? res.data : [];
            if (bookings.length === 0) { empty.classList.remove('hidden'); list.innerHTML = ''; return; }
            empty.classList.add('hidden');
            list.innerHTML = bookings.map(b => `
            <div class="card card-content">
                <h3>Reservation #${b.id.substring(0, 8)}</h3>
                <p>Check-in: ${b.checkInDate}</p>
                <p>Check-out: ${b.checkOutDate}</p>
                <span class="status-badge status-confirmed">${b.status}</span>
            </div>
        `).join('');
        } catch (err) {
            empty.classList.add('hidden');
            list.innerHTML = '<div class="card card-content">No se pudieron cargar las reservas.</div>';
        }
    });
}

function showRooms() {
    document.getElementById('rooms-section').classList.remove('hidden');
    document.getElementById('bookings-section').classList.add('hidden');
}
