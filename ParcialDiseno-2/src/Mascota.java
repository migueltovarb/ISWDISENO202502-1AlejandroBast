import java.util.ArrayList;
import java.util.List;

public class Mascota {
    private String nombre;
    private String especie;
    private int edad;
    private Dueno dueno;                               // 1 dueño
    private final List<ControlVeterinario> controles = new ArrayList<>();

    public Mascota(Dueno dueno, String nombre, String especie, int edad) {
        this.dueno = dueno;
        this.nombre = nombre;
        this.especie = especie;
        this.edad = edad;
    }

    public Dueno getDueno() {
        return dueno;
    }

    public void setDueno(Dueno d) {
         this.dueno = d;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String n) {
        this.nombre = n;
    }

    public String getEspecie() {
        return especie;
    }
    public void setEspecie(String e) {
        this.especie = e;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int e) {
        this.edad = e;
    }

    public List<ControlVeterinario> getControles() {
        return new ArrayList<>(controles);
    }

    public void agregarControl(ControlVeterinario c) {
        controles.add(c);
    }

    @Override
    public String toString(){
        return "Mascota{nombre=" + nombre + "Especie=" + especie +
                ", edad=" + edad + ", dueno=" + dueno.getNombre() + "}";
    }
}
