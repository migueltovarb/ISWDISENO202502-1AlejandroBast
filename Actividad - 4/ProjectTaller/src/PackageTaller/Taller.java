package PackageTaller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

public class Taller {
    private final RepositorioMemoria repo;

    public Taller() {
        this.repo = new RepositorioMemoria();
    }

    public Taller(RepositorioMemoria repo) {
        this.repo = (repo == null) ? new RepositorioMemoria() : repo;
    }

    // --- Propietarios ---
    public Propietario registrarPropietario(String nombreCompleto, String contacto) {
        String nombre = (nombreCompleto == null) ? "" : nombreCompleto.trim();
        String cont = (contacto == null) ? "" : contacto.trim();

        if (nombre.isEmpty()) throw new IllegalArgumentException("El nombre completo es obligatorio.");
        if (cont.isEmpty()) throw new IllegalArgumentException("El número de contacto es obligatorio.");

        Propietario nuevo = new Propietario(UUID.randomUUID().toString(), nombre, cont);
        repo.guardarPropietario(nuevo);
        return nuevo;
    }

    // --- Vehículos ---
    public Vehiculo registrarVehiculo(String placa, String marca, String modelo, String propietarioId) {
        String pla = ((placa == null) ? "" : placa.trim()).toUpperCase(Locale.ROOT);
        String mar = (marca == null) ? "" : marca.trim();
        String mod = (modelo == null) ? "" : modelo.trim();

        if (pla.isEmpty()) throw new IllegalArgumentException("La placa es obligatoria.");
        if (repo.existePlaca(pla)) throw new IllegalArgumentException("La placa '" + pla + "' ya está registrada.");

        Propietario propietario = repo.obtenerPropietario(propietarioId);
        if (propietario == null) throw new IllegalArgumentException("El propietario no existe.");

        Vehiculo veh = new Vehiculo(pla, mar, mod, propietario);
        repo.guardarVehiculo(veh);
        return veh;
    }

    // --- Servicios ---
    public Servicio registrarServicio(String placa, TipoServicio tipo, double costo, LocalDate fecha, String descripcion) {
        String pla = ((placa == null) ? "" : placa.trim()).toUpperCase(Locale.ROOT);
        Vehiculo veh = repo.obtenerVehiculo(pla);
        if (veh == null)
            throw new IllegalArgumentException("No se puede registrar un servicio a un vehículo que no existe.");

        Servicio servicio = new Servicio(tipo, costo, fecha, descripcion);
        veh.agregarServicio(servicio);
        return servicio;
    }

    // --- Consultas/Reportes ---
    public List<Servicio> obtenerHistorialPorVehiculo(String placa) {
        String pla = ((placa == null) ? "" : placa.trim()).toUpperCase(Locale.ROOT);
        Vehiculo veh = repo.obtenerVehiculo(pla);
        if (veh == null) throw new IllegalArgumentException("El vehículo no existe.");
        return veh.historialServicios();
    }

    public double calcularTotalPorVehiculo(String placa) {
        String pla = ((placa == null) ? "" : placa.trim()).toUpperCase(Locale.ROOT);
        Vehiculo veh = repo.obtenerVehiculo(pla);
        if (veh == null) throw new IllegalArgumentException("El vehículo no existe.");
        return veh.totalGastado();
    }

    public List<ReporteAtencion> generarReporteAtencion() {
        List<ReporteAtencion> reporte = new ArrayList<>();
        for (Vehiculo v : repo.vehiculos().values()) {
            double total = v.totalGastado();
            int cantidad = v.historialServicios().size();
            reporte.add(new ReporteAtencion(
                    v.getPlaca(), v.getMarca(), v.getModelo(),
                    v.getPropietario().getNombreCompleto(), total, cantidad
            ));
        }
        reporte.sort(null); // usa compareTo: ordenado por total descendente
        return reporte;
    }
}
