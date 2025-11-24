<p align="center">
  <img width="400" height="400" alt="hoteles" src="https://github.com/user-attachments/assets/2b1e0b9e-2c87-40ad-91e5-0f5500b90153" />
</p>

# SuitYa!

Aplicación web de reservas de hotel construida con Spring Boot (backend), MongoDB y un frontend estático (HTML/CSS/JS) servido desde `src/main/resources/static`. Incluye autenticación basada en sesión, gestión de habitaciones y reservas, y un panel para administradores.

## Características
- Autenticación con formulario de login y registro.
- Dashboard con listado de habitaciones, filtros (tipo, búsqueda, orden por precio) y reserva mediante modal con validaciones.
- Sección “Mis reservas” con estados de carga, vacío y badges.
- Panel de administración para crear y eliminar habitaciones.
- Manejo centralizado de peticiones y errores en el cliente.
- Accesibilidad mejorada: foco visible, `alt` en imágenes y `loading="lazy"`.

## Arquitectura
- Backend: Spring Boot 3, Spring Security, Spring Data MongoDB.
- Frontend: HTML/CSS/JS servido por Spring (carpeta `static`).
- Seguridad: login con `formLogin` y protección de rutas (`/admin.html` y endpoints admin requieren `ROLE_ADMIN`).

## Estructura del proyecto
- `src/main/java/com/hotel/reservation/`
  - `config/SecurityConfig.java` configuración de seguridad.
  - `controller/*.java` controladores REST (auth, rooms, reservations).
  - `model/*.java` entidades (Room, Reservation, User).
  - `repository/*.java` repositorios Mongo.
- `src/main/resources/`
  - `static/` HTML, CSS y JS del frontend.
  - `application.properties` configuración de la aplicación.
- `pom.xml` dependencias y plugins.

## Endpoints principales
- Autenticación:
  - `POST /api/auth/login` (form) → redirige al dashboard.
  - `POST /api/auth/register` (JSON) → crea usuario.
  - `GET /api/auth/current-user` → usuario actual.
  - `POST /api/auth/logout` → cierra sesión.
- Habitaciones:
  - `GET /api/rooms` → lista de habitaciones.
  - `POST /api/rooms` (ADMIN) → crear habitación.
  - `DELETE /api/rooms/{id}` (ADMIN) → borrar habitación.
- Reservas:
  - `POST /api/reservations` → crear reserva.
  - `GET /api/reservations/my` → reservas del usuario.

## Frontend: puntos clave
- `static/js/app.js`
  - `apiFetch(url, options)` manejo de `401`, parseo JSON/Texto y retorno uniforme.
  - Login/registro: envío de formularios y mensajes de feedback.
  - `checkAuth()` inserta enlace Admin según rol y muestra saludo.
  - `loadRooms()` obtiene datos y `applyRoomFilters()` aplica tipo, búsqueda y orden.
  - `bookRoom(roomId)` abre modal, valida fechas y crea la reserva.
  - “Mis reservas” con estados de carga y vacío.

## Configuración
1. Java y Maven instalados.
2. MongoDB: configurar credenciales y base de datos en `src/main/resources/application.properties`:
   - `spring.data.mongodb.uri=...`
   - `spring.data.mongodb.database=...`
3. Actualizar `server.port` si tu 8080 está ocupado (puedes arrancar el JAR con `--server.port=8081`).

## Ejecución
- Desarrollo:
  - `mvn -DskipTests package`
  - `java -jar target/reservation-0.0.1-SNAPSHOT.jar --server.port=8081`
- Acceso:
  - `http://localhost:8081/index.html` (login)
  - `http://localhost:8081/dashboard.html` (dashboard)
  - `http://localhost:8081/admin.html` (admin, requiere `ROLE_ADMIN`)

## Seguridad
- No expongas credenciales en repositorios públicos.
- Mantén la protección de rutas en backend; el cliente solo oculta/enseña enlaces.

## Personalización de marca
- El branding del proyecto se actualizó a “SuitYa!”.
- Títulos HTML y navbar muestran “SuitYa!”.
- El icono se incluye al inicio de este README.

## Licencia
Este proyecto es para fines educativos/demostrativos. Ajusta la licencia según tus necesidades.

