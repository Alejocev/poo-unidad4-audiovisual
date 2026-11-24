package modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SerieDeTV extends ContenidoAudiovisual {
    private final List<Temporada> temporadas;

    public SerieDeTV(String titulo, int duracionEnMinutos, String genero) {
        super(titulo, duracionEnMinutos, genero);
        this.temporadas = new ArrayList<>();
    }

    public void agregarTemporada(Temporada temporada) {
        if (temporada == null) {
            throw new IllegalArgumentException("La temporada no puede ser nula.");
        }
        this.temporadas.add(temporada);
    }

    public List<Temporada> getTemporadas() {
        return Collections.unmodifiableList(temporadas);
    }

    public int getTotalTemporadas() {
        return temporadas.size();
    }

    @Override
    public String getTipo() {
        return "SerieDeTV";
    }

    @Override
    public String obtenerDetalles() {
        StringBuilder sb = new StringBuilder();
        sb.append("[Serie de TV] ").append(obtenerInfoBase())
          .append(" | Temporadas: ").append(temporadas.size());
        if (!temporadas.isEmpty()) {
            sb.append("\n   Detalle temporadas: ");
            for (int i = 0; i < temporadas.size(); i++) {
                sb.append(temporadas.get(i).toString());
                if (i < temporadas.size() - 1) sb.append(", ");
            }
        }
        return sb.toString();
    }
}