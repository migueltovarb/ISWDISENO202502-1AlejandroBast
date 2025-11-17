package PaqueteLibro;

public class Libro {
    private String nombre;
    private String autor;
    private double precio;
    private int cantidad = 0;

    public Libro(String nombre, String autor, double precio) {
        this.nombre = nombre;
        this.autor = autor;
        this.precio = precio;
    }

    public Libro(String nombre, String autor, double precio, int cantidad) {
        this.nombre = nombre;
        this.autor = autor;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    public String getNombre() { return nombre; }
    public String getAutor() { return autor; }
    public double getPrecio() { return precio; }
    public int getCantidad() { return cantidad; }

    public void setPrecio(double precio) { this.precio = precio; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    @Override
    public String toString() {
        return "Libro[nombre=" + nombre +
               ", autor=" + autor +
               ", precio=" + precio +
               ", cantidad=" + cantidad + "]";
    }
}
