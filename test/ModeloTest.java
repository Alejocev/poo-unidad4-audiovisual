import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import modelo.*;

public class ModeloTest {

    @Test
    public void testCrearPeliculaYAgregarActor() {
        Pelicula pelicula = new Pelicula("Inception", 148, "Ciencia Ficción", "Warner Bros");
        Actor actor = new Actor("Leonardo", "DiCaprio", "Estadounidense", 49);
        pelicula.agregarActor(actor);

        assertEquals("Inception", pelicula.getTitulo());
        assertEquals(148, pelicula.getDuracionEnMinutos());
        assertEquals(1, pelicula.getActores().size());
        assertEquals("Leonardo DiCaprio", pelicula.getActores().get(0).getNombreCompleto());
    }

    @Test
    public void testCrearSerieDeTVYTemporadas() {
        SerieDeTV serie = new SerieDeTV("Dark", 55, "Suspenso");
        serie.agregarTemporada(new Temporada(1, 10));
        serie.agregarTemporada(new Temporada(2, 8));

        assertEquals(2, serie.getTotalTemporadas());
        assertEquals(10, serie.getTemporadas().get(0).getCantidadEpisodios());
    }

    @Test
    public void testCrearDocumentalYInvestigador() {
        Documental doc = new Documental("Cosmos", 60, "Ciencia", "Astronomía");
        doc.agregarInvestigador(new Investigador("Carl", "Sagan", "Astrofísica", 30));

        assertEquals("Astronomía", doc.getTema());
        assertEquals(1, doc.getInvestigadores().size());
        assertTrue(doc.obtenerDetalles().contains("Carl Sagan"));
    }

    @Test
    public void testCrearAnimeYPodcast() {
        Anime anime = new Anime("Death Note", 23, "Suspenso", "Madhouse", true);
        Podcast podcast = new Podcast("The Wild Project", 120, "Entrevistas", "YouTube", 200);

        assertTrue(anime.isEsAdaptacionManga());
        assertEquals("Spotify".equals(podcast.getPlataforma()), false);
        assertEquals(200, podcast.getNumeroEpisodio());
    }

    @Test
    public void testValidacionesExcepciones() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Pelicula("", 120, "Acción", "Sony");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new Pelicula("Matrix", -10, "Acción", "Warner");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new Actor("Keanu", "Reeves", "Canadiense", 0);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new Temporada(0, 10);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new Investigador("Ana", "Gómez", "Historia", -5);
        });
    }
}