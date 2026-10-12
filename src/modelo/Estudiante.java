
package modelo;

/**
 * Representa a un estudiante del sistema
 * Biblioteca Escolar.
 * Hereda los atributos y metodos de Usuario.
 *
 * @author Sergio Sandoval
 */
public class Estudiante extends Usuario {

    private String curso;

    /**
     * Constructor de Estudiante.
     *
     * @param id identificador del estudiante
     * @param nombre nombre del estudiante
     * @param rut RUT del estudiante
     * @param curso curso del estudiante
     * @param correo correo electronico
     * @param contrasena contrasena del usuario
     */
    public Estudiante(int id, String nombre, String rut,
                      String curso, String correo, String contrasena) {

        super(id, nombre, rut, correo, contrasena);
        this.curso = curso;
    }

    /**
     * Obtiene el curso del estudiante.
     *
     * @return curso del estudiante
     */
    public String getCurso() {
        return curso;
    }

    /**
     * Modifica el curso del estudiante.
     *
     * @param curso nuevo curso del estudiante
     */
    public void setCurso(String curso) {
        this.curso = curso;
    }

    /**
     * Obtiene el rol del estudiante.
     *
     * @return rol estudiante
     */
    @Override
    public String getRol() {
        return "estudiante";
    }
}
