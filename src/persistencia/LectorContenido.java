package persistencia;

import java.io.IOException;
import java.util.List;
import modelo.ContenidoAudiovisual;

public interface LectorContenido {
    List<ContenidoAudiovisual> cargar(String rutaArchivo) throws IOException;
}