import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ClinicaVeterinaria {
    private final List<Mascota> pacientes = new ArrayList<>();
    private final List<Dueno> duenos = new ArrayList<>();

    public List<Mascota> getPacientes() {
        return new ArrayList<>(pacientes);
    }

    public List<Dueno> getDuenos() {
        return new ArrayList<>(duenos);
    }

    public Dueno registrarDueno(String nombre, String documento, String telefono) {
        for (int i = 0; i < duenos.size(); i++) {
            if (duenos.get(i).getDocumento().equalsIgnoreCase(documento)) {
                throw new IllegalStateException("Ya existe un dueño con ese documento");
            }
        }
        Dueno d = new Dueno(nombre, documento, telefono);
        duenos.add(d);
        return d;
    }

    public Mascota registrarMascota(Dueno dueno, String nombre, String especie, int edad) {
        for (int i = 0; i < pacientes.size(); i++) {
            Mascota m = pacientes.get(i);
            boolean mismoDueno = m.getDueno().getDocumento().equalsIgnoreCase(dueno.getDocumento());
            boolean mismoNombre = m.getNombre().equalsIgnoreCase(nombre);
            if (mismoDueno && mismoNombre) {
                throw new IllegalStateException("Ya existe una mascota con ese nombre para este dueño");
            }
        }
        Mascota nueva = new Mascota(dueno, nombre, especie, edad);
        pacientes.add(nueva);
        return nueva;
    }

    public ControlVeterinario registrarControl(Mascota mascota, LocalDate fecha, String tipo, String obs) {
        if (!pacientes.contains(mascota))
            throw new IllegalArgumentException("Mascota inexistente en la clínica");
        ControlVeterinario c = new ControlVeterinario(fecha, tipo, obs);
        mascota.agregarControl(c);
        return c;
    }

    public List<ControlVeterinario> historial(Mascota mascota) {
        if (!pacientes.contains(mascota))
            throw new IllegalArgumentException("Mascota inexistente en la clínica");
        return mascota.getControles();
    }
}
