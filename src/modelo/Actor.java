package modelo;

public class Actor {
    private String nombre;
    private String apellido;
    private String nacionalidad;
    private int edad;

    public Actor(String nombre, String apellido, String nacionalidad, int edad) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del actor no puede estar vacío.");
        }
        if (edad <= 0) {
            throw new IllegalArgumentException("La edad del actor debe ser mayor a 0.");
        }
        this.nombre = nombre.trim();
        this.apellido = apellido != null ? apellido.trim() : "";
        this.nacionalidad = nacionalidad != null ? nacionalidad.trim() : "";
        this.edad = edad;
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

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        if (edad <= 0) {
            throw new IllegalArgumentException("La edad del actor debe ser mayor a 0.");
        }
        this.edad = edad;
    }

    public String getNombreCompleto() {
        return (nombre + " " + apellido).trim();
    }

    @Override
    public String toString() {
        return String.format("%s (%s, %d años)", getNombreCompleto(), nacionalidad, edad);
    }
}