package paqueteMiPrimerProyecto;

import java.util.Scanner;

public class TiendaDescuento {

    private static final double DESCUENTO_ROPA = 0.10;
    private static final double DESCUENTO_TECNOLOGIA = 0.05;
    private static final double DESCUENTO_ALIMENTOS = 0.02;

    private static final double UMBRAL_TOTAL = 500000.0;
    private static final double DESCUENTO_ADICIONAL = 0.05;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("TIENDA VIRTUAL");

        int numProductos;
        do {
            System.out.print("Ingrese el número de productos a comprar (mínimo 1): ");
            numProductos = sc.nextInt();
        } while (numProductos < 1);

        double[] precios = new double[numProductos];

        double totalSinDescuento = 0;
        double totalConDescuento = 0;

        int contador = 0;
        while (contador < numProductos) {
            sc.nextLine();
            System.out.println("\nProducto " + (contador + 1));

            System.out.print("Ingrese el nombre del producto: ");
            String nombre = sc.nextLine();

            System.out.print("Ingrese el tipo de producto (1: Ropa, 2: Tecnología, 3: Alimentos): ");
            int tipo = sc.nextInt();

            System.out.print("Ingrese el precio del producto: ");
            double precio = sc.nextDouble();

            precios[contador] = precio;
            totalSinDescuento += precio;

            double descuentoProducto = 0;
            switch (tipo) {
                case 1:
                    descuentoProducto = precio * DESCUENTO_ROPA;
                    break;
                case 2:
                    descuentoProducto = precio * DESCUENTO_TECNOLOGIA;
                    break;
                case 3:
                    descuentoProducto = precio * DESCUENTO_ALIMENTOS;
                    break;
                default:
                    System.out.println("Tipo inválido, no se aplica descuento.");
                    break;
            }

            totalConDescuento += (precio - descuentoProducto);
            contador++;
        }

        if (totalConDescuento > UMBRAL_TOTAL) {
            double descuentoExtra = totalConDescuento * DESCUENTO_ADICIONAL;
            totalConDescuento -= descuentoExtra;
            System.out.println("\n¡Felicidades! Obtienes un descuento adicional del 5% por compras superiores a $500.000.");
        }

        double ahorro = totalSinDescuento - totalConDescuento;

        System.out.println("\n=== RESUMEN DE LA COMPRA ===");
        System.out.println("Total sin descuentos: $" + totalSinDescuento);
        System.out.println("Total con descuentos: $" + totalConDescuento);
        System.out.println("Ahorro total: $" + ahorro);

        sc.close();
    }
}
