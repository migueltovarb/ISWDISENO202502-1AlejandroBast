public class Dueno {
    private String documento;   // ahora este es el identificador
    private String nombre;
    private String telefono;

    public Dueno(String nombre, String documento, String telefono) {
        this.nombre = nombre;
        this.documento = documento;
        this.telefono = telefono;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nNombre) {
        this.nombre = nNombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String nTelefono) {
        this.telefono = nTelefono;
    }

    @Override
    public String toString(){
        return "Dueno{ documento='" + documento + "', nombre='" + nombre + "telefono=" + telefono + "'}";
    }
}
