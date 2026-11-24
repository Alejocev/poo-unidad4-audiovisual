package persistencia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import modelo.*;

public class GestorArchivosCSV implements LectorContenido, EscritorContenido {

    private static final String ENCABEZADO = "TIPO,TITULO,DURACION,GENERO,EXTRA1,EXTRA2,RELACIONADOS";

    @Override
    public List<ContenidoAudiovisual> cargar(String rutaArchivo) throws IOException {
        List<ContenidoAudiovisual> lista = new ArrayList<>();
        File archivo = new File(rutaArchivo);
        if (!archivo.exists()) {
            return lista;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea = br.readLine(); // Saltar encabezado
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) {
                    continue;
                }
                ContenidoAudiovisual contenido = parsearLinea(linea);
                if (contenido != null) {
                    lista.add(contenido);
                }
            }
        }
        return lista;
    }

    @Override
    public void guardar(String rutaArchivo, List<ContenidoAudiovisual> contenidos) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo))) {
            bw.write(ENCABEZADO);
            bw.newLine();
            for (ContenidoAudiovisual contenido : contenidos) {
                bw.write(formatearLinea(contenido));
                bw.newLine();
            }
        }
    }

    private ContenidoAudiovisual parsearLinea(String linea) {
        String[] campos = linea.split(",", -1);
        if (campos.length < 7) {
            return null;
        }

        String tipo = campos[0].trim();
        String titulo = campos[1].trim();
        int duracion = Integer.parseInt(campos[2].trim());
        String genero = campos[3].trim();
        String extra1 = campos[4].trim();
        String extra2 = campos[5].trim();
        String relacionados = campos[6].trim();

        switch (tipo) {
            case "Pelicula":
                return crearPelicula(titulo, duracion, genero, extra1, relacionados);
            case "SerieDeTV":
                return crearSerie(titulo, duracion, genero, relacionados);
            case "Documental":
                return crearDocumental(titulo, duracion, genero, extra1, relacionados);
            case "Anime":
                return new Anime(titulo, duracion, genero, extra1, Boolean.parseBoolean(extra2));
            case "Podcast":
                return new Podcast(titulo, duracion, genero, extra1, Integer.parseInt(extra2));
            default:
                return null;
        }
    }

    private Pelicula crearPelicula(String titulo, int duracion, String genero, String estudio, String actoresStr) {
        Pelicula pelicula = new Pelicula(titulo, duracion, genero, estudio);
        if (!actoresStr.isEmpty()) {
            for (String item : actoresStr.split(";")) {
                String[] datos = item.split("\\|");
                if (datos.length == 4) {
                    pelicula.agregarActor(new Actor(datos[0], datos[1], datos[2], Integer.parseInt(datos[3])));
                }
            }
        }
        return pelicula;
    }

    private SerieDeTV crearSerie(String titulo, int duracion, String genero, String temporadasStr) {
        SerieDeTV serie = new SerieDeTV(titulo, duracion, genero);
        if (!temporadasStr.isEmpty()) {
            for (String item : temporadasStr.split(";")) {
                String[] datos = item.split("\\|");
                if (datos.length == 2) {
                    serie.agregarTemporada(new Temporada(Integer.parseInt(datos[0]), Integer.parseInt(datos[1])));
                }
            }
        }
        return serie;
    }

    private Documental crearDocumental(String titulo, int duracion, String genero, String tema, String invStr) {
        Documental documental = new Documental(titulo, duracion, genero, tema);
        if (!invStr.isEmpty()) {
            for (String item : invStr.split(";")) {
                String[] datos = item.split("\\|");
                if (datos.length == 4) {
                    documental.agregarInvestigador(
                            new Investigador(datos[0], datos[1], datos[2], Integer.parseInt(datos[3])));
                }
            }
        }
        return documental;
    }

    private String formatearLinea(ContenidoAudiovisual c) {
        String base = String.format("%s,%s,%d,%s,", c.getTipo(), c.getTitulo(), c.getDuracionEnMinutos(), c.getGenero());
        if (c instanceof Pelicula p) {
            return base + p.getEstudio() + ",," + formatearActores(p.getActores());
        } else if (c instanceof SerieDeTV s) {
            return base + ",," + formatearTemporadas(s.getTemporadas());
        } else if (c instanceof Documental d) {
            return base + d.getTema() + ",," + formatearInvestigadores(d.getInvestigadores());
        } else if (c instanceof Anime a) {
            return base + a.getEstudioAnimacion() + "," + a.isEsAdaptacionManga() + ",";
        } else if (c instanceof Podcast pod) {
            return base + pod.getPlataforma() + "," + pod.getNumeroEpisodio() + ",";
        }
        return base + ",,";
    }

    private String formatearActores(List<Actor> actores) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < actores.size(); i++) {
            Actor a = actores.get(i);
            sb.append(a.getNombre()).append("|").append(a.getApellido()).append("|")
              .append(a.getNacionalidad()).append("|").append(a.getEdad());
            if (i < actores.size() - 1) sb.append(";");
        }
        return sb.toString();
    }

    private String formatearTemporadas(List<Temporada> temporadas) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < temporadas.size(); i++) {
            Temporada t = temporadas.get(i);
            sb.append(t.getNumeroTemporada()).append("|").append(t.getCantidadEpisodios());
            if (i < temporadas.size() - 1) sb.append(";");
        }
        return sb.toString();
    }

    private String formatearInvestigadores(List<Investigador> investigadores) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < investigadores.size(); i++) {
            Investigador inv = investigadores.get(i);
            sb.append(inv.getNombre()).append("|").append(inv.getApellido()).append("|")
              .append(inv.getEspecialidad()).append("|").append(inv.getAniosExperiencia());
            if (i < investigadores.size() - 1) sb.append(";");
        }
        return sb.toString();
    }
}