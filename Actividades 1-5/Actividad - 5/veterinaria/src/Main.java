import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        ClinicaVeterinaria app = new ClinicaVeterinaria();

        Dueno ana = app.registrarDueno("Ana Pérez", "CC123", "3001234567");
        Mascota fido = app.registrarMascota(ana, "Fido", "Perro", 3);

        app.registrarControl(fido, LocalDate.now(), "VACUNA", "Refuerzo anual");
        app.registrarControl(fido, LocalDate.now().plusMonths(6), "CHEQUEO", "Control general");

        System.out.println("Historial de " + fido.getNombre() + ": " + app.historial(fido));

        try {
            app.registrarMascota(ana, "Fido", "Gato", 1);
        } catch (Exception e) {
            System.out.println("OK validación: " + e.getMessage());
        }

        try {
            app.registrarDueno("Ana Alter", "CC123", "3000000000");
        } catch (Exception e) {
            System.out.println("OK documento único: " + e.getMessage());
        }
    }
}
