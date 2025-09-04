import java.util.Scanner;

public class GestorAsistencias {
    static final int DIAS_SEMANA = 5;
    static final int NUM_ESTUDIANTES = 4;
    static String[][] listaAsistencia = new String[NUM_ESTUDIANTES][DIAS_SEMANA];

    static Scanner scan = new Scanner(System.in);

    public static void registrarAsistencia() {
        for (int est = 0; est < NUM_ESTUDIANTES; est++) {
            System.out.println("Estudiante N°: " + (est + 1));
            for (int dia = 0; dia < DIAS_SEMANA; dia++) {
                String asist;
                System.out.print("Día N°: " + (dia + 1) + " (P/A): ");
                asist = scan.nextLine().toUpperCase();
                listaAsistencia[est][dia] = asist;
            }
        }
    }

    public static void resumenAsistencia() {
        System.out.println("Resumen asistencia:");
        int faltasTotal = 0;
        for (int est = 0; est < NUM_ESTUDIANTES; est++) {
            int cont = 0;
            for (int dia = 0; dia < DIAS_SEMANA; dia++) {
                if (listaAsistencia[est][dia].equals("P")) {
                    cont++;
                }
            }

            if (cont == DIAS_SEMANA) {
                System.out.println("Estudiante N°" + (est + 1) + " NO FALTÓ NINGÚN DÍA");
            } else {
                System.out.println("Estudiante N°" + (est + 1) + ": " + cont + " Asistencias");
            }

            faltasTotal += (DIAS_SEMANA - cont);
        }
        System.out.println("Faltas totales de la semana: " + faltasTotal + " faltas");
    }

    public static void verAsistenciaIndividual() {
        for (int est = 0; est < NUM_ESTUDIANTES; est++) {
            int cont = 0;
            for (int dia = 0; dia < DIAS_SEMANA; dia++) {
                if (listaAsistencia[est][dia].equals("P")) {
                    cont++;
                }
            }

            if (cont == DIAS_SEMANA) {
                System.out.println("Estudiante N°" + (est + 1) + " NO FALTÓ NINGÚN DÍA");
            } else {
                System.out.println("Estudiante N°" + (est + 1) + ": " + cont + " Asistencias");
            }
        }
    }

    public static void menu() {
        int opcion;
        do {
            System.out.println("\n--- Menú ---");
            System.out.println("1. Ver asistencia individual");
            System.out.println("2. Ver resumen general");
            System.out.println("3. Volver a registrar");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scan.nextInt();
            scan.nextLine();

            switch (opcion) {
                case 1 -> verAsistenciaIndividual();
                case 2 -> resumenAsistencia();
                case 3 -> registrarAsistencia();
                case 4 -> System.out.println("Saliendo del sistema.");
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 4);
    }

    public static void main(String[] args) {
        registrarAsistencia();
        menu();
    }
}
