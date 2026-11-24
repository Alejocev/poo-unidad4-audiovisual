package modelo;

public class Anime extends ContenidoAudiovisual {
    private String estudioAnimacion;
    private boolean esAdaptacionManga;

    public Anime(String titulo, int duracionEnMinutos, String genero, String estudioAnimacion, boolean esAdaptacionManga) {
        super(titulo, duracionEnMinutos, genero);
        if (estudioAnimacion == null || estudioAnimacion.trim().isEmpty()) {
            throw new IllegalArgumentException("El estudio de animación no puede estar vacío.");
        }
        this.estudioAnimacion = estudioAnimacion.trim();
        this.esAdaptacionManga = esAdaptacionManga;
    }

    public String getEstudioAnimacion() {
        return estudioAnimacion;
    }

    public void setEstudioAnimacion(String estudioAnimacion) {
        if (estudioAnimacion == null || estudioAnimacion.trim().isEmpty()) {
            throw new IllegalArgumentException("El estudio de animación no puede estar vacío.");
        }
        this.estudioAnimacion = estudioAnimacion.trim();
    }

    public boolean isEsAdaptacionManga() {
        return esAdaptacionManga;
    }

    public void setEsAdaptacionManga(boolean esAdaptacionManga) {
        this.esAdaptacionManga = esAdaptacionManga;
    }

    @Override
    public String getTipo() {
        return "Anime";
    }

    @Override
    public String obtenerDetalles() {
        return String.format("[Anime] %s | Estudio: %s | Adaptación de manga: %s",
                obtenerInfoBase(), estudioAnimacion, esAdaptacionManga ? "Sí" : "No");
    }
}