package PackageTaller;

public class ReporteAtencion implements Comparable<ReporteAtencion> {
    private final String placa;
    private final String marca;
    private final String modelo;
    private final String propietario;
    private final double totalGastado;
    private final int cantidadServicios;

    public ReporteAtencion(String placa, String marca, String modelo, String propietario,
                           double totalGastado, int cantidadServicios) {
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.propietario = propietario;
        this.totalGastado = totalGastado;
        this.cantidadServicios = cantidadServicios;
    }

    public String getPlaca() { return placa; }
    public String getMarca() { return marca; }
    public String getModelo() { return modelo; }
    public String getPropietario() { return propietario; }
    public double getTotalGastado() { return totalGastado; }
    public int getCantidadServicios() { return cantidadServicios; }

    @Override
    public int compareTo(ReporteAtencion o) {
        return Double.compare(o.totalGastado, this.totalGastado); // descendente por total
    }
}
