package PackageTaller;

import java.time.LocalDate;
import java.util.List;

public class MainDemo {
    public static void main(String[] args) {
        Taller taller = new Taller();

        // Registrar servicios (costo > 0)
        taller.registrarServicio("ABC123", TipoServicio.CAMBIO_ACEITE, 120_000, LocalDate.of(2025, 3, 10), "");
        taller.registrarServicio("ABC123", TipoServicio.SISTEMA_FRENOS, 380_000, LocalDate.of(2025, 4, 2), "Cambio pastillas y rectificación");

        // Consultas
        System.out.println("Historial de servicios de ABC123:");
        taller.obtenerHistorialPorVehiculo("ABC123").forEach(s ->
                System.out.printf("- %s | %s | $%,.0f | %s%n",
                        s.getFecha(), s.getTipo().getEtiqueta(), s.getCosto(), s.getDescripcion())
        );

        double total = taller.calcularTotalPorVehiculo("ABC123");
        System.out.printf("%nTotal gastado en ABC123: $%,.0f%n", total);

        List<ReporteAtencion> reporte = taller.generarReporteAtencion();
        System.out.println("\nReporte de atención (mayor gasto primero):");
        for (ReporteAtencion r : reporte) {
            System.out.printf("Placa %s | %s %s | Prop: %s | Total: $%,.0f | Servicios: %d%n",
                    r.getPlaca(), r.getMarca(), r.getModelo(), r.getPropietario(), r.getTotalGastado(), r.getCantidadServicios());
        }
    }
}
