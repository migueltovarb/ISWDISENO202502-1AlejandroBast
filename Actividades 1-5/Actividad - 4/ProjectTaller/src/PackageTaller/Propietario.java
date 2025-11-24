package PackageTaller;

import java.util.Objects;

public class Propietario {
    private final String id;
    private final String nombreCompleto;
    private String contacto;

    public Propietario(String id, String nombreCompleto, String contacto) {
        if (nombreCompleto == null || nombreCompleto.trim().isEmpty())
            throw new IllegalArgumentException("El nombre completo es obligatorio.");
        if (contacto == null || contacto.trim().isEmpty())
            throw new IllegalArgumentException("El número de contacto es obligatorio.");

        this.id = Objects.requireNonNull(id, "id no puede ser null");
        this.nombreCompleto = nombreCompleto.trim();
        this.contacto = contacto.trim();
    }

    public String getId() { return id; }
    public String getNombreCompleto() { return nombreCompleto; }
    public String getContacto() { return contacto; }

    public void actualizarContacto(String nuevoContacto) {
        if (nuevoContacto == null || nuevoContacto.trim().isEmpty())
            throw new IllegalArgumentException("El contacto no puede estar vacío.");
        this.contacto = nuevoContacto.trim();
    }
}
