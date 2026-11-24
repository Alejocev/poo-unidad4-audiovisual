package modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pelicula extends ContenidoAudiovisual {
    private String estudio;
    private final List<Actor> actores;

    public Pelicula(String titulo, int duracionEnMinutos, String genero, String estudio) {
        super(titulo, duracionEnMinutos, genero);
        if (estudio == null || estudio.trim().isEmpty()) {
            throw new IllegalArgumentException("El estudio no puede estar vacío.");
        }
        this.estudio = estudio.trim();
        this.actores = new ArrayList<>();
    }

    public void agregarActor(Actor actor) {
        if (actor == null) {
            throw new IllegalArgumentException("El actor no puede ser nulo.");
        }
        this.actores.add(actor);
    }

    public String getEstudio() {
        return estudio;
    }

    public void setEstudio(String estudio) {
        if (estudio == null || estudio.trim().isEmpty()) {
            throw new IllegalArgumentException("El estudio no puede estar vacío.");
        }
        this.estudio = estudio.trim();
    }

    public List<Actor> getActores() {
        return Collections.unmodifiableList(actores);
    }

    @Override
    public String getTipo() {
        return "Pelicula";
    }

    @Override
    public String obtenerDetalles() {
        StringBuilder sb = new StringBuilder();
        sb.append("[Película] ").append(obtenerInfoBase())
          .append(" | Estudio: ").append(estudio);
        if (!actores.isEmpty()) {
            sb.append("\n   Actores: ");
            for (int i = 0; i < actores.size(); i++) {
                sb.append(actores.get(i).toString());
                if (i < actores.size() - 1) sb.append(", ");
            }
        }
        return sb.toString();
    }
}