package PackageTaller;

import java.time.LocalDate;

public class Servicio {
    private final TipoServicio tipo;
    private final double costo;
    private final LocalDate fecha;
    private final String descripcion;

    public Servicio(TipoServicio tipo, double costo, LocalDate fecha, String descripcion) {
        if (costo <= 0) throw new IllegalArgumentException("El costo del servicio debe ser mayor a 0.");
        if (fecha == null) throw new IllegalArgumentException("La fecha es obligatoria.");
        if (tipo == null) throw new IllegalArgumentException("El tipo de servicio es obligatorio.");

        this.tipo = tipo;
        this.costo = costo;
        this.fecha = fecha;
        this.descripcion = (descripcion == null) ? "" : descripcion.trim();
    }

    public TipoServicio getTipo() { return tipo; }
    public double getCosto() { return costo; }
    public LocalDate getFecha() { return fecha; }
    public String getDescripcion() { return descripcion; }
}
