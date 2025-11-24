package PaqueteEmpleado;

public class ProgramaEmpleado {
    public static void main(String[] args) {
        Empleado emp1 = new Empleado(872680, "Santiago", "Bustos", 2000);

        System.out.println(emp1);

        System.out.println("Salario anual: " + emp1.getSalarioAnual());

        emp1.aumentarSalario(10);
        System.out.println("Después del aumento: " + emp1);

        emp1.setSalario(3000);
        System.out.println("Nuevo salario: " + emp1);
    }
}
