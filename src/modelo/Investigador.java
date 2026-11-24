package modelo;

public class Investigador {
    private String nombre;
    private String apellido;
    private String especialidad;
    private int aniosExperiencia;

    public Investigador(String nombre, String apellido, String especialidad, int aniosExperiencia) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del investigador no puede estar vacío.");
        }
        if (aniosExperiencia < 0) {
            throw new IllegalArgumentException("Los años de experiencia no pueden ser negativos.");
        }
        this.nombre = nombre.trim();
        this.apellido = apellido != null ? apellido.trim() : "";
        this.especialidad = especialidad != null ? especialidad.trim() : "";
        this.aniosExperiencia = aniosExperiencia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public int getAniosExperiencia() {
        return aniosExperiencia;
    }

    public void setAniosExperiencia(int aniosExperiencia) {
        if (aniosExperiencia < 0) {
            throw new IllegalArgumentException("Los años de experiencia no pueden ser negativos.");
        }
        this.aniosExperiencia = aniosExperiencia;
    }

    public String getNombreCompleto() {
        return (nombre + " " + apellido).trim();
    }

    @Override
    public String toString() {
        return String.format("%s - Especialidad: %s (%d años exp.)",
                getNombreCompleto(), especialidad, aniosExperiencia);
    }
}