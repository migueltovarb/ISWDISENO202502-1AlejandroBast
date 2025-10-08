import java.time.LocalDate;

public class ControlVeterinario {

    private final LocalDate fecha;
    private final String tipo;            // String
    private String observaciones;

    public ControlVeterinario(LocalDate fecha, String tipo, String observaciones) {
        this.fecha = fecha;
        this.tipo = tipo;
        this.observaciones = observaciones;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public String getTipo() {
        return tipo;
    }
    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    @Override
    public String toString(){
        return "Control{" + "fecha=" + fecha + ", tipo='" + tipo + "', obs='" + observaciones + "'}";
    }
}
