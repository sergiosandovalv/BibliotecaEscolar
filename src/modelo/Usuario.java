
package modelo;

/**
 * Clase abstracta que representa un usuario
 * del sistema Biblioteca Escolar.
 *
 * @author Sergio Sandoval
 */
public abstract class Usuario {

    private int id;
    private String nombre;
    private String rut;
    private String correo;
    private String contrasena;

    /**
     * Constructor de la clase Usuario.
     *
     * @param id identificador del usuario
     * @param nombre nombre del usuario
     * @param rut RUT del usuario
     * @param correo correo electronico
     * @param contrasena contrasena del usuario
     */
    public Usuario(int id, String nombre, String rut,
                   String correo, String contrasena) {
        this.id = id;
        this.nombre = nombre;
        this.rut = rut;
        this.correo = correo;
        this.contrasena = contrasena;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    /**
     * Obtiene el rol del usuario.
     *
     * @return rol del usuario
     */
    public abstract String getRol();
}
