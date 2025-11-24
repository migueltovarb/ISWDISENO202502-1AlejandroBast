package PaqueteLibro;

public class ProgramaLibro {
    public static void main(String[] args) {
        Libro libro1 = new Libro("Cien años de soledad", "Gabriel García Márquez", 150000, 20);
        Libro libro2 = new Libro("El coronel no tiene quien le escriba", "Gabriel García Márquez", 90000);

        System.out.println(libro1);
        System.out.println(libro2);

        libro2.setPrecio(95000);
        libro2.setCantidad(15);
        System.out.println("Libro actualizado: " + libro2);
    }
}
