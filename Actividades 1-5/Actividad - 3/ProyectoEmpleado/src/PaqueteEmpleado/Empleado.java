package PaqueteEmpleado;

public class Empleado {
    private int id;
    private String primerNombre;
    private String apellido;
    private int salario;

    public Empleado(int id, String primerNombre, String apellido, int salario) {
        this.id = id;
        this.primerNombre = primerNombre;
        this.apellido = apellido;
        this.salario = salario;
    }

    public int getID() { return id; }
    public String getPrimerNombre() { return primerNombre; }
    public String getApellido() { return apellido; }
    public String getNombre() { return primerNombre + " " + apellido; }
    public int getSalario() { return salario; }

    public void setSalario(int salario) { this.salario = salario; }

    public int getSalarioAnual() { return salario * 12; }

    public int aumentarSalario(int porcentaje) {
        salario += salario * porcentaje / 100;
        return salario;
    }

    @Override
    public String toString() {
        return "Empleado[id=" + id + ", nombre=" + getNombre() + ", salario=" + salario + "]";
    }
}
