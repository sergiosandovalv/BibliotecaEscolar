
package dao;

import datos.ConexionBD;
import modelo.Libro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Gestiona los libros almacenados
 * en la base de datos biblioteca.
 *
 * @author Sergio Sandoval
 */
public class LibroDAO {

    /**
     * Obtiene todos los libros registrados.
     *
     * @return lista de libros
     */
    public List<Libro> listarTodos() {

        List<Libro> libros = new ArrayList<>();

        String sql = "SELECT id, titulo, autor, isbn, "
                + "editorial, stock, id_categoria "
                + "FROM libros ORDER BY id";

        try (Connection conexion =
                     ConexionBD.getInstancia().obtenerConexion();
             PreparedStatement sentencia =
                     conexion.prepareStatement(sql);
             ResultSet resultado = sentencia.executeQuery()) {

            while (resultado.next()) {

                Libro libro = new Libro(
                        resultado.getInt("id"),
                        resultado.getString("titulo"),
                        resultado.getString("autor"),
                        resultado.getString("isbn"),
                        resultado.getString("editorial"),
                        resultado.getInt("stock"),
                        resultado.getInt("id_categoria")
                );

                libros.add(libro);
            }

        } catch (SQLException e) {
            System.out.println("Error al consultar los libros.");
            System.out.println(e.getMessage());
        }

        return libros;
    }

    /**
     * Guarda un nuevo libro en la base de datos.
     *
     * @param libro libro que se desea registrar
     * @return true si se registra correctamente
     */
    public boolean guardar(Libro libro) {

        String sql = "INSERT INTO libros "
                + "(titulo, autor, isbn, editorial, stock, id_categoria) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conexion =
                     ConexionBD.getInstancia().obtenerConexion();
             PreparedStatement sentencia =
                     conexion.prepareStatement(sql)) {

            sentencia.setString(1, libro.getTitulo());
            sentencia.setString(2, libro.getAutor());
            sentencia.setString(3, libro.getIsbn());
            sentencia.setString(4, libro.getEditorial());
            sentencia.setInt(5, libro.getStock());
            sentencia.setInt(6, libro.getIdCategoria());

            return sentencia.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al guardar el libro.");
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * Actualiza la informacion de un libro.
     *
     * @param libro libro con los datos actualizados
     * @return true si se actualiza correctamente
     */
    public boolean actualizar(Libro libro) {

        if (libro.getStock() < 0) {
            return false;
        }

        String sql = "UPDATE libros SET "
                + "titulo = ?, autor = ?, isbn = ?, "
                + "editorial = ?, stock = ?, id_categoria = ? "
                + "WHERE id = ?";

        try (Connection conexion =
                     ConexionBD.getInstancia().obtenerConexion();
             PreparedStatement sentencia =
                     conexion.prepareStatement(sql)) {

            sentencia.setString(1, libro.getTitulo());
            sentencia.setString(2, libro.getAutor());
            sentencia.setString(3, libro.getIsbn());
            sentencia.setString(4, libro.getEditorial());
            sentencia.setInt(5, libro.getStock());
            sentencia.setInt(6, libro.getIdCategoria());
            sentencia.setInt(7, libro.getId());

            return sentencia.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al actualizar el libro.");
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * Actualiza el stock de un libro.
     *
     * @param idLibro identificador del libro
     * @param nuevoStock nueva cantidad disponible
     * @return true si se actualiza correctamente
     */
    public boolean actualizarStock(int idLibro, int nuevoStock) {

        if (nuevoStock < 0) {
            return false;
        }

        String sql = "UPDATE libros SET stock = ? WHERE id = ?";

        try (Connection conexion =
                     ConexionBD.getInstancia().obtenerConexion();
             PreparedStatement sentencia =
                     conexion.prepareStatement(sql)) {

            sentencia.setInt(1, nuevoStock);
            sentencia.setInt(2, idLibro);

            return sentencia.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al actualizar el stock.");
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * Elimina un libro de la base de datos.
     *
     * @param idLibro identificador del libro
     * @return true si se elimina correctamente
     */
    public boolean eliminar(int idLibro) {

        String sql = "DELETE FROM libros WHERE id = ?";

        try (Connection conexion =
                     ConexionBD.getInstancia().obtenerConexion();
             PreparedStatement sentencia =
                     conexion.prepareStatement(sql)) {

            sentencia.setInt(1, idLibro);

            return sentencia.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar el libro.");
            System.out.println(e.getMessage());
            return false;
        }
    }
}
