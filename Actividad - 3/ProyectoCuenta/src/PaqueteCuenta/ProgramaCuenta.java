package PaqueteCuenta;

public class ProgramaCuenta {
    public static void main(String[] args) {
        Cuenta cuenta1 = new Cuenta("C001", "Santiago Bustos", 500000);
        Cuenta cuenta2 = new Cuenta("C002", " Nicolas Bastidas", 200000);

        System.out.println(cuenta1);
        System.out.println(cuenta2);

        cuenta1.abonar(100000);
        System.out.println("Después de abonar a Santiago: " + cuenta1);

        cuenta2.retirar(50000);
        System.out.println("Después de retirar a Nicolas: " + cuenta2);

        cuenta1.transferirA(cuenta2, 200000);
        System.out.println("Después de transferir:");
        System.out.println(cuenta1);
        System.out.println(cuenta2);
    }
}
