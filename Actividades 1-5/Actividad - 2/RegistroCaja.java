import java.util.*;

public class RegistroCaja {
    static ArrayList<String> names = new ArrayList<>();
    static ArrayList<String> types = new ArrayList<>();
    static ArrayList<Integer> prices = new ArrayList<>();

    static final double descuentRopa = 0.5;
    static final double descuentFood = 0.10;
    static final double descuentecnologia = 0.2;

    static Scanner scan = new Scanner(System.in);

    public static void ingresoProductos() {
        while (true) {
            System.out.println("Ingrese el nombre del producto:");
            String name = scan.nextLine();
            names.add(name);

            System.out.println("Ingrese el tipo de producto:");
            String type = scan.nextLine();
            types.add(type);

            System.out.println("Ingrese el precio del producto:");
            int price = scan.nextInt();
            prices.add(price);
            scan.nextLine();

            System.out.println("¿Desea ingresar otro producto? (si/no)");
            String respuesta = scan.nextLine();
            if (respuesta.equalsIgnoreCase("no")) {
                break;
            }
        }
    }

    public static int sumarPrices() {
        int total = 0;
        for (int p : prices) {
            total += p;
        }
        System.out.println("El precio total de la compra sin descuentos es: " + total);
        return total;
    }

    public static void aplicarDescuentos() {
        int totalConDescuento = 0;

        for (int i = 0; i < prices.size(); i++) {
            String tipo = types.get(i);
            int precio = prices.get(i);
            double descuento = 0;

            if (tipo.equalsIgnoreCase("ropa")) {
                descuento = descuentRopa;
            } else if (tipo.equalsIgnoreCase("comida")) {
                descuento = descuentFood;
            } else if (tipo.equalsIgnoreCase("tecnologia")) {
                descuento = descuentecnologia;
            }

            int precioFinal = (int) (precio - (precio * descuento));
            totalConDescuento += precioFinal;

            if (precioFinal >= 500000) {
                descuento = precioFinal * 0.8;
                totalConDescuento -= descuento;
            }
        }

        System.out.println("El precio total con descuentos aplicados es: " + totalConDescuento);
    }

    public static void main(String[] args) {
        boolean activo = true;

        while (activo) {
            System.out.println("\n--- Menú ---");
            System.out.println("1. Ingresar productos");
            System.out.println("2. Ver total sin descuentos");
            System.out.println("3. Aplicar descuentos");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = scan.nextInt();
            scan.nextLine();

            switch (opcion) {
                case 1:
                    ingresoProductos();
                    break;
                case 2:
                    sumarPrices();
                    break;
                case 3:
                    aplicarDescuentos();
                    break;
                case 4:
                    System.out.println("Saliendo del programa...");
                    activo = false;
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }
}
