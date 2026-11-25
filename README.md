# Sistema de Gestión de Contenido Audiovisual - POO (Unidad 4)

Sistema en Java refactorizado bajo principios de **Código Limpio**, **SOLID**, arquitectura **MVC**, persistencia en archivos **CSV** y pruebas unitarias con **JUnit**.

## Estructura del Proyecto
- `src/modelo/`: Clases de negocio (`ContenidoAudiovisual`, `Pelicula`, `SerieDeTV`, `Documental`, `Anime`, `Podcast`, `Actor`, `Temporada`, `Investigador`).
- `src/persistencia/`: Interfaces `LectorContenido` y `EscritorContenido` (ISP/DIP) e implementación `GestorArchivosCSV`.
- `src/vista/`: `VistaConsola` (interfaz de usuario en consola) y `Main` (punto de entrada).
- `src/controlador/`: `ControladorContenido` (coordina el modelo, la vista y la persistencia).
- `test/`: Pruebas unitarias (`ModeloTest` y `GestorArchivosCSVTest`).

## Mejoras Implementadas
- **Manejo de Archivos CSV:** Lectura y escritura de todos los contenidos y sus entidades asociadas (Actores, Temporadas e Investigadores) con manejo de excepciones (`IOException`).
- **Código Limpio:** Eliminación de código duplicado (`obtenerInfoBase()`), validaciones en constructores, nombres descriptivos y eliminación de comentarios redundantes.
- **Principios SOLID:** SRP (el modelo no imprime en consola), OCP/LSP (herencia limpia desde `ContenidoAudiovisual`), ISP (interfaces separadas para lectura y escritura) y DIP (inyección de dependencias en `ControladorContenido`).
- **Patrón MVC:** Separación estricta entre datos, lógica de control e interfaz de consola.

## Cómo Clonar y Ejecutar el Proyecto
1. Clonar el repositorio:
   ```bash
   git clone https://github.com/Alejocev/poo-unidad4-audiovisual.git
   ```
2. Importar el proyecto en **Eclipse IDE** (`File -> Open Projects from File System...`).
3. Ubicar la clase `src/vista/Main.java`.
4. Hacer clic derecho sobre `Main.java` -> **Run As** -> **Java Application**.

## Cómo Ejecutar las Pruebas Unitarias
1. En el *Package Explorer* de Eclipse, hacer clic derecho sobre la carpeta `test/`.
2. Seleccionar **Run As** -> **JUnit Test**.
3. Verificar en la pestaña **JUnit** que los 7 casos de prueba (`ModeloTest` y `GestorArchivosCSVTest`) finalicen en verde.
