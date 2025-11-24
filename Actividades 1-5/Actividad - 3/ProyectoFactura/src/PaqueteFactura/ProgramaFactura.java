package PaqueteFactura;

import java.text.NumberFormat;
import java.util.Locale;

public class ProgramaFactura {
    public static void main(String[] args) {
        NumberFormat formatoCOP = NumberFormat.getCurrencyInstance(new Locale("es", "CO"));

        // Crear ítems de factura
        ItemFactura item1 = new ItemFactura("A001", "Laptop", 2, 3200000.0);
        ItemFactura item2 = new ItemFactura("B002", "Celular", 5, 1200000.0);

        System.out.println(item1);
        System.out.println("Total item1: " + formatoCOP.format(item1.getTotal()));

        System.out.println(item2);
        System.out.println("Total item2: " + formatoCOP.format(item2.getTotal()));

        item2.setCantidad(10);
        item2.setPrecioUnitario(1100000.0);
        System.out.println("Item2 actualizado: " + item2);
        System.out.println("Nuevo total item2: " + formatoCOP.format(item2.getTotal()));
    }
}
