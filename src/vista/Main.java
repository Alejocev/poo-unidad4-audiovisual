package vista;

import controlador.ControladorContenido;
import persistencia.GestorArchivosCSV;

public class Main {
    public static void main(String[] args) {
        GestorArchivosCSV gestorCSV = new GestorArchivosCSV();
        VistaConsola vista = new VistaConsola();
        ControladorContenido controlador = new ControladorContenido(gestorCSV, gestorCSV, vista);
        controlador.iniciar();
    }
}