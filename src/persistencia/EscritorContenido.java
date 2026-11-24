package persistencia;

import java.io.IOException;
import java.util.List;
import modelo.ContenidoAudiovisual;

public interface EscritorContenido {
    void guardar(String rutaArchivo, List<ContenidoAudiovisual> contenidos) throws IOException;
}