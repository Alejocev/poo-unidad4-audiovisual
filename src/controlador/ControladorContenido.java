package controlador;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import modelo.*;
import persistencia.EscritorContenido;
import persistencia.LectorContenido;
import vista.VistaConsola;

public class ControladorContenido {
    private static final String RUTA_CSV = "contenidos.csv";
    private final List<ContenidoAudiovisual> contenidos;
    private final LectorContenido lector;
    private final EscritorContenido escritor;
    private final VistaConsola vista;

    public ControladorContenido(LectorContenido lector, EscritorContenido escritor, VistaConsola vista) {
        this.lector = lector;
        this.escritor = escritor;
        this.vista = vista;
        this.contenidos = new ArrayList<>();
    }

    public void iniciar() {
        cargarDatosIniciales();
        int opcion;
        do {
            vista.mostrarMenu();
            opcion = vista.leerEntero("");
            procesarOpcion(opcion);
        } while (opcion != 0);
    }

    private void cargarDatosIniciales() {
        try {
            List<ContenidoAudiovisual> cargados = lector.cargar(RUTA_CSV);
            if (!cargados.isEmpty()) {
                contenidos.addAll(cargados);
                vista.mostrarMensaje("Datos cargados desde " + RUTA_CSV + " (" + cargados.size() + " registros).");
            } else {
                inicializarDatosDeEjemplo();
                vista.mostrarMensaje("Archivo CSV no encontrado. Se cargaron datos de ejemplo.");
            }
        } catch (IOException e) {
            vista.mostrarMensaje("Error al leer el archivo: " + e.getMessage());
            inicializarDatosDeEjemplo();
        }
    }

    private void inicializarDatosDeEjemplo() {
        Pelicula pelicula = new Pelicula("Interstellar", 169, "Ciencia Ficción", "Warner Bros");
        pelicula.agregarActor(new Actor("Matthew", "McConaughey", "Estadounidense", 54));

        SerieDeTV serie = new SerieDeTV("Breaking Bad", 47, "Drama");
        serie.agregarTemporada(new Temporada(1, 7));
        serie.agregarTemporada(new Temporada(2, 13));

        Documental documental = new Documental("Los Misterios del Océano", 90, "Ciencia", "Biología Marina");
        documental.agregarInvestigador(new Investigador("Elena", "Ríos", "Biología", 10));

        Anime anime = new Anime("Shingeki no Kyojin", 24, "Acción", "MAPPA", true);
        Podcast podcast = new Podcast("Entiende tu Mente", 30, "Psicología", "Spotify", 150);

        contenidos.add(pelicula);
        contenidos.add(serie);
        contenidos.add(documental);
        contenidos.add(anime);
        contenidos.add(podcast);
    }

    private void procesarOpcion(int opcion) {
        try {
            switch (opcion) {
                case 1 -> vista.mostrarContenidos(contenidos);
                case 2 -> agregarPelicula();
                case 3 -> agregarSerieDeTV();
                case 4 -> agregarDocumental();
                case 5 -> agregarAnime();
                case 6 -> agregarPodcast();
                case 7 -> guardarEnArchivo();
                case 8 -> recargarDesdeArchivo();
                case 0 -> vista.mostrarMensaje("Saliendo del sistema...");
                default -> vista.mostrarMensaje("Opción no válida.");
            }
        } catch (IllegalArgumentException e) {
            vista.mostrarMensaje("Error de validación: " + e.getMessage());
        }
    }

    private void agregarPelicula() {
        String titulo = vista.leerTexto("Título: ");
        int duracion = vista.leerEntero("Duración (min): ");
        String genero = vista.leerTexto("Género: ");
        String estudio = vista.leerTexto("Estudio: ");
        Pelicula pelicula = new Pelicula(titulo, duracion, genero, estudio);

        if (vista.leerBooleano("¿Desea agregar un actor?")) {
            String nombre = vista.leerTexto("Nombre del actor: ");
            String apellido = vista.leerTexto("Apellido del actor: ");
            String nacionalidad = vista.leerTexto("Nacionalidad: ");
            int edad = vista.leerEntero("Edad: ");
            pelicula.agregarActor(new Actor(nombre, apellido, nacionalidad, edad));
        }
        contenidos.add(pelicula);
        vista.mostrarMensaje("Película agregada correctamente.");
    }

    private void agregarSerieDeTV() {
        String titulo = vista.leerTexto("Título: ");
        int duracion = vista.leerEntero("Duración promedio por episodio (min): ");
        String genero = vista.leerTexto("Género: ");
        SerieDeTV serie = new SerieDeTV(titulo, duracion, genero);

        if (vista.leerBooleano("¿Desea agregar una temporada?")) {
            int numTemp = vista.leerEntero("Número de temporada: ");
            int episodios = vista.leerEntero("Cantidad de episodios: ");
            serie.agregarTemporada(new Temporada(numTemp, episodios));
        }
        contenidos.add(serie);
        vista.mostrarMensaje("Serie de TV agregada correctamente.");
    }

    private void agregarDocumental() {
        String titulo = vista.leerTexto("Título: ");
        int duracion = vista.leerEntero("Duración (min): ");
        String genero = vista.leerTexto("Género: ");
        String tema = vista.leerTexto("Tema: ");
        Documental documental = new Documental(titulo, duracion, genero, tema);

        if (vista.leerBooleano("¿Desea agregar un investigador?")) {
            String nombre = vista.leerTexto("Nombre: ");
            String apellido = vista.leerTexto("Apellido: ");
            String especialidad = vista.leerTexto("Especialidad: ");
            int experiencia = vista.leerEntero("Años de experiencia: ");
            documental.agregarInvestigador(new Investigador(nombre, apellido, especialidad, experiencia));
        }
        contenidos.add(documental);
        vista.mostrarMensaje("Documental agregado correctamente.");
    }

    private void agregarAnime() {
        String titulo = vista.leerTexto("Título: ");
        int duracion = vista.leerEntero("Duración (min): ");
        String genero = vista.leerTexto("Género: ");
        String estudio = vista.leerTexto("Estudio de animación: ");
        boolean manga = vista.leerBooleano("¿Es adaptación de manga?");
        contenidos.add(new Anime(titulo, duracion, genero, estudio, manga));
        vista.mostrarMensaje("Anime agregado correctamente.");
    }

    private void agregarPodcast() {
        String titulo = vista.leerTexto("Título: ");
        int duracion = vista.leerEntero("Duración (min): ");
        String genero = vista.leerTexto("Género: ");
        String plataforma = vista.leerTexto("Plataforma: ");
        int episodio = vista.leerEntero("Número de episodio: ");
        contenidos.add(new Podcast(titulo, duracion, genero, plataforma, episodio));
        vista.mostrarMensaje("Podcast agregado correctamente.");
    }

    private void guardarEnArchivo() {
        try {
            escritor.guardar(RUTA_CSV, contenidos);
            vista.mostrarMensaje("Datos guardados exitosamente en " + RUTA_CSV);
        } catch (IOException e) {
            vista.mostrarMensaje("Error al guardar el archivo: " + e.getMessage());
        }
    }

    private void recargarDesdeArchivo() {
        try {
            List<ContenidoAudiovisual> cargados = lector.cargar(RUTA_CSV);
            contenidos.clear();
            contenidos.addAll(cargados);
            vista.mostrarMensaje("Se recargaron " + cargados.size() + " registros desde " + RUTA_CSV);
        } catch (IOException e) {
            vista.mostrarMensaje("Error al cargar el archivo: " + e.getMessage());
        }
    }
}