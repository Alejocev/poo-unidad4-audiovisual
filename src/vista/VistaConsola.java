package vista;

import java.util.List;
import java.util.Scanner;
import modelo.ContenidoAudiovisual;

public class VistaConsola {
    private final Scanner scanner;

    public VistaConsola() {
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        System.out.println("\n=== SISTEMA DE CONTENIDO AUDIOVISUAL ===");
        System.out.println("1. Listar todos los contenidos");
        System.out.println("2. Agregar Película (con Actor)");
        System.out.println("3. Agregar Serie de TV (con Temporada)");
        System.out.println("4. Agregar Documental (con Investigador)");
        System.out.println("5. Agregar Anime");
        System.out.println("6. Agregar Podcast");
        System.out.println("7. Guardar datos en CSV");
        System.out.println("8. Cargar datos desde CSV");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");
    }

    public int leerEntero(String mensaje) {
        while (true) {
            if (!mensaje.isEmpty()) {
                System.out.print(mensaje);
            }
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingrese un número entero válido.");
            }
        }
    }

    public String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine().trim();
    }

    public boolean leerBooleano(String mensaje) {
        System.out.print(mensaje + " (s/n): ");
        String respuesta = scanner.nextLine().trim().toLowerCase();
        return respuesta.equals("s") || respuesta.equals("si") || respuesta.equals("sí");
    }

    public void mostrarContenidos(List<ContenidoAudiovisual> contenidos) {
        System.out.println("\n--- LISTA DE CONTENIDOS AUDIOVISUALES ---");
        if (contenidos.isEmpty()) {
            System.out.println("No hay contenidos registrados.");
            return;
        }
        for (ContenidoAudiovisual contenido : contenidos) {
            System.out.println(contenido.obtenerDetalles());
        }
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
}