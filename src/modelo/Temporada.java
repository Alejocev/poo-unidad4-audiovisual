package modelo;

public class Temporada {
    private int numeroTemporada;
    private int cantidadEpisodios;

    public Temporada(int numeroTemporada, int cantidadEpisodios) {
        if (numeroTemporada <= 0) {
            throw new IllegalArgumentException("El número de temporada debe ser mayor a 0.");
        }
        if (cantidadEpisodios <= 0) {
            throw new IllegalArgumentException("La cantidad de episodios debe ser mayor a 0.");
        }
        this.numeroTemporada = numeroTemporada;
        this.cantidadEpisodios = cantidadEpisodios;
    }

    public int getNumeroTemporada() {
        return numeroTemporada;
    }

    public void setNumeroTemporada(int numeroTemporada) {
        if (numeroTemporada <= 0) {
            throw new IllegalArgumentException("El número de temporada debe ser mayor a 0.");
        }
        this.numeroTemporada = numeroTemporada;
    }

    public int getCantidadEpisodios() {
        return cantidadEpisodios;
    }

    public void setCantidadEpisodios(int cantidadEpisodios) {
        if (cantidadEpisodios <= 0) {
            throw new IllegalArgumentException("La cantidad de episodios debe ser mayor a 0.");
        }
        this.cantidadEpisodios = cantidadEpisodios;
    }

    @Override
    public String toString() {
        return String.format("Temporada %d (%d episodios)", numeroTemporada, cantidadEpisodios);
    }
}