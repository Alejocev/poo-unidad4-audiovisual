package modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Documental extends ContenidoAudiovisual {
    private String tema;
    private final List<Investigador> investigadores;

    public Documental(String titulo, int duracionEnMinutos, String genero, String tema) {
        super(titulo, duracionEnMinutos, genero);
        if (tema == null || tema.trim().isEmpty()) {
            throw new IllegalArgumentException("El tema del documental no puede estar vacío.");
        }
        this.tema = tema.trim();
        this.investigadores = new ArrayList<>();
    }

    public void agregarInvestigador(Investigador investigador) {
        if (investigador == null) {
            throw new IllegalArgumentException("El investigador no puede ser nulo.");
        }
        this.investigadores.add(investigador);
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        if (tema == null || tema.trim().isEmpty()) {
            throw new IllegalArgumentException("El tema del documental no puede estar vacío.");
        }
        this.tema = tema.trim();
    }

    public List<Investigador> getInvestigadores() {
        return Collections.unmodifiableList(investigadores);
    }

    @Override
    public String getTipo() {
        return "Documental";
    }

    @Override
    public String obtenerDetalles() {
        StringBuilder sb = new StringBuilder();
        sb.append("[Documental] ").append(obtenerInfoBase())
          .append(" | Tema: ").append(tema);
        if (!investigadores.isEmpty()) {
            sb.append("\n   Investigadores: ");
            for (int i = 0; i < investigadores.size(); i++) {
                sb.append(investigadores.get(i).toString());
                if (i < investigadores.size() - 1) sb.append(", ");
            }
        }
        return sb.toString();
    }
}