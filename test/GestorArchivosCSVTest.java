import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import modelo.*;
import persistencia.GestorArchivosCSV;

public class GestorArchivosCSVTest {

    private static final String ARCHIVO_PRUEBA = "test_contenidos.csv";
    private final GestorArchivosCSV gestor = new GestorArchivosCSV();

    @AfterEach
    public void limpiarArchivo() {
        File archivo = new File(ARCHIVO_PRUEBA);
        if (archivo.exists()) {
            archivo.delete();
        }
    }

    @Test
    public void testGuardarYCargarCSVCompleto() throws IOException {
        List<ContenidoAudiovisual> originales = new ArrayList<>();

        Pelicula p = new Pelicula("Avatar", 162, "Aventura", "20th Century");
        p.agregarActor(new Actor("Sam", "Worthington", "Australiano", 47));

        SerieDeTV s = new SerieDeTV("Stranger Things", 50, "Ciencia Ficción");
        s.agregarTemporada(new Temporada(1, 8));

        Documental d = new Documental("Planeta Tierra", 50, "Naturaleza", "Fauna");
        d.agregarInvestigador(new Investigador("David", "Attenborough", "Biología", 60));

        originales.add(p);
        originales.add(s);
        originales.add(d);
        originales.add(new Anime("Naruto", 24, "Shonen", "Pierrot", true));
        originales.add(new Podcast("TED Talks", 20, "Educación", "Spotify", 45));

        gestor.guardar(ARCHIVO_PRUEBA, originales);
        List<ContenidoAudiovisual> cargados = gestor.cargar(ARCHIVO_PRUEBA);

        assertEquals(5, cargados.size());
        assertEquals("Avatar", cargados.get(0).getTitulo());
        assertEquals(1, ((Pelicula) cargados.get(0)).getActores().size());
        assertEquals(1, ((SerieDeTV) cargados.get(1)).getTotalTemporadas());
        assertEquals(1, ((Documental) cargados.get(2)).getInvestigadores().size());
    }

    @Test
    public void testCargarArchivoInexistenteDevuelveListaVacia() throws IOException {
        List<ContenidoAudiovisual> resultado = gestor.cargar("archivo_que_no_existe.csv");
        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
    }
}