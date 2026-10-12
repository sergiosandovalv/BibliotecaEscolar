
package modelo;

/**
 * Representa a un bibliotecario del sistema.
 * Hereda los atributos y metodos de Usuario.
 *
 * @author Sergio Sandoval
 */
public class Bibliotecario extends Usuario {

    /**
     * Constructor de Bibliotecario.
     *
     * @param id identificador del usuario
     * @param nombre nombre del bibliotecario
     * @param rut RUT del bibliotecario
     * @param correo correo electronico
     * @param contrasena contrasena del usuario
     */
    public Bibliotecario(int id, String nombre, String rut,
                         String correo, String contrasena) {

        super(id, nombre, rut, correo, contrasena);
    }

    /**
     * Obtiene el rol del bibliotecario.
     *
     * @return rol bibliotecario
     */
    @Override
    public String getRol() {
        return "bibliotecario";
    }
}
