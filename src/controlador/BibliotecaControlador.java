
package controlador;

import dao.EstudianteDAO;
import dao.LibroDAO;
import dao.PrestamoDAO;
import modelo.Estudiante;
import modelo.Libro;
import modelo.Prestamo;

import java.util.List;

/**
 * Controlador principal del sistema BibliotecaEscolar.
 * Comunica las operaciones del sistema con las clases DAO.
 *
 * @author Sergio Sandoval
 */
public class BibliotecaControlador {

    private final EstudianteDAO estudianteDAO;
    private final LibroDAO libroDAO;
    private final PrestamoDAO prestamoDAO;

    /**
     * Inicializa los objetos DAO.
     */
    public BibliotecaControlador() {
        estudianteDAO = new EstudianteDAO();
        libroDAO = new LibroDAO();
        prestamoDAO = new PrestamoDAO();
    }

    /**
     * Obtiene los estudiantes registrados.
     *
     * @return lista de estudiantes
     */
    public List<Estudiante> listarEstudiantes() {
        return estudianteDAO.listarTodos();
    }

    /**
     * Registra un nuevo estudiante.
     *
     * @param nombre nombre del estudiante
     * @param rut RUT del estudiante
     * @param curso curso del estudiante
     * @param correo correo electronico
     * @return true si se registra correctamente
     */
    public boolean registrarEstudiante(String nombre, String rut,
                                       String curso, String correo) {

        return estudianteDAO.guardar(nombre, rut, curso, correo);
    }

    /**
     * Obtiene los libros registrados.
     *
     * @return lista de libros
     */
    public List<Libro> listarLibros() {
        return libroDAO.listarTodos();
    }

    /**
     * Registra un nuevo libro.
     *
     * @param libro libro que se desea registrar
     * @return true si se registra correctamente
     */
    public boolean registrarLibro(Libro libro) {
        return libroDAO.guardar(libro);
    }

    /**
     * Obtiene los prestamos registrados.
     *
     * @return lista de prestamos
     */
    public List<Prestamo> listarPrestamos() {
        return prestamoDAO.listarTodos();
    }

    /**
     * Registra un prestamo.
     *
     * @param prestamo prestamo que se desea registrar
     * @return true si se registra correctamente
     */
    public boolean registrarPrestamo(Prestamo prestamo) {
        return prestamoDAO.guardar(prestamo);
    }

    /**
     * Registra la devolucion de un libro.
     *
     * @param idPrestamo identificador del prestamo
     * @return true si se registra correctamente
     */
    public boolean registrarDevolucion(int idPrestamo) {
        return prestamoDAO.registrarDevolucion(idPrestamo);
    }

    /**
     * Actualiza la informacion de un libro.
     *
     * @param libro libro con los datos actualizados
     * @return true si se actualiza correctamente
     */
    public boolean actualizarLibro(Libro libro) {
        return libroDAO.actualizar(libro);
    }
}
