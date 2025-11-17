package PaqueteAutor;

public class ProgramaAutor {
    public static void main(String[] args) {
        Autor autor1 = new Autor("Santiago Bustos López", "santyago@gmail.com", 'm');

        System.out.println(autor1);

        autor1.setEmail("santyago@gmail.com");
        System.out.println("Autor actualizado: " + autor1);

        System.out.println("Nombre: " + autor1.getNombre());
        System.out.println("Email: " + autor1.getEmail());
        System.out.println("Género: " + autor1.getGenero());
    }
}
