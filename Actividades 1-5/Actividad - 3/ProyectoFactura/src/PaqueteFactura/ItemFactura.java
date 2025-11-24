package PaqueteFactura;

public class ItemFactura {
    private String id;
    private String descripcion;
    private int cantidad;
    private double precioUnitario;

    public ItemFactura(String id, String descripcion, int cantidad, double precioUnitario) {
        this.id = id;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }

    public String getID() { return id; }
    public String getDescripcion() { return descripcion; }
    public int getCantidad() { return cantidad; }
    public double getPrecioUnitario() { return precioUnitario; }

    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
    public void setPrecioUnitario(double precioUnitario) { this.precioUnitario = precioUnitario; }

    public double getTotal() {
        return precioUnitario * cantidad;
    }

    @Override
    public String toString() {
        return "ItemFactura[id=" + id + ", descripcion=" + descripcion + 
               ", cantidad=" + cantidad + ", precioUnitario=" + precioUnitario + "]";
    }
}
