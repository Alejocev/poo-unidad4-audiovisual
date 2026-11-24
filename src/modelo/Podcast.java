package modelo;

public class Podcast extends ContenidoAudiovisual {
    private String plataforma;
    private int numeroEpisodio;

    public Podcast(String titulo, int duracionEnMinutos, String genero, String plataforma, int numeroEpisodio) {
        super(titulo, duracionEnMinutos, genero);
        if (plataforma == null || plataforma.trim().isEmpty()) {
            throw new IllegalArgumentException("La plataforma no puede estar vacía.");
        }
        if (numeroEpisodio <= 0) {
            throw new IllegalArgumentException("El número de episodio debe ser mayor a 0.");
        }
        this.plataforma = plataforma.trim();
        this.numeroEpisodio = numeroEpisodio;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(String plataforma) {
        if (plataforma == null || plataforma.trim().isEmpty()) {
            throw new IllegalArgumentException("La plataforma no puede estar vacía.");
        }
        this.plataforma = plataforma.trim();
    }

    public int getNumeroEpisodio() {
        return numeroEpisodio;
    }

    public void setNumeroEpisodio(int numeroEpisodio) {
        if (numeroEpisodio <= 0) {
            throw new IllegalArgumentException("El número de episodio debe ser mayor a 0.");
        }
        this.numeroEpisodio = numeroEpisodio;
    }

    @Override
    public String getTipo() {
        return "Podcast";
    }

    @Override
    public String obtenerDetalles() {
        return String.format("[Podcast] %s | Plataforma: %s | Episodio #%d",
                obtenerInfoBase(), plataforma, numeroEpisodio);
    }
}