package modelo;

public abstract class ContenidoAudiovisual {
    private static int contadorId = 1;
    private final int id;
    private String titulo;
    private int duracionEnMinutos;
    private String genero;

    public ContenidoAudiovisual(String titulo, int duracionEnMinutos, String genero) {
        validarDatosBase(titulo, duracionEnMinutos, genero);
        this.id = contadorId++;
        this.titulo = titulo.trim();
        this.duracionEnMinutos = duracionEnMinutos;
        this.genero = genero.trim();
    }

    private void validarDatosBase(String titulo, int duracionEnMinutos, String genero) {
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new IllegalArgumentException("El título no puede estar vacío.");
        }
        if (duracionEnMinutos <= 0) {
            throw new IllegalArgumentException("La duración debe ser mayor a 0 minutos.");
        }
        if (genero == null || genero.trim().isEmpty()) {
            throw new IllegalArgumentException("El género no puede estar vacío.");
        }
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new IllegalArgumentException("El título no puede estar vacío.");
        }
        this.titulo = titulo.trim();
    }

    public int getDuracionEnMinutos() {
        return duracionEnMinutos;
    }

    public void setDuracionEnMinutos(int duracionEnMinutos) {
        if (duracionEnMinutos <= 0) {
            throw new IllegalArgumentException("La duración debe ser mayor a 0 minutos.");
        }
        this.duracionEnMinutos = duracionEnMinutos;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        if (genero == null || genero.trim().isEmpty()) {
            throw new IllegalArgumentException("El género no puede estar vacío.");
        }
        this.genero = genero.trim();
    }

    protected String obtenerInfoBase() {
        return String.format("ID: %d | Título: %s | Duración: %d min | Género: %s",
                id, titulo, duracionEnMinutos, genero);
    }

    public abstract String getTipo();

    public abstract String obtenerDetalles();
}