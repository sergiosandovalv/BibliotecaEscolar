
package dao;

import datos.ConexionBD;
import modelo.Estudiante;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Gestiona las consultas, registro y actualizacion
 * de estudiantes en la base de datos biblioteca.
 *
 * @author Sergio Sandoval
 */
public class EstudianteDAO {

    /**
     * Obtiene todos los estudiantes registrados.
     *
     * @return lista de estudiantes
     */
    public List<Estudiante> listarTodos() {

        List<Estudiante> estudiantes = new ArrayList<>();

        String sql = "SELECT id, nombre, rut, curso, correo "
                + "FROM estudiantes ORDER BY id";

        try (Connection conexion =
                     ConexionBD.getInstancia().obtenerConexion();
             PreparedStatement sentencia =
                     conexion.prepareStatement(sql);
             ResultSet resultado = sentencia.executeQuery()) {

            while (resultado.next()) {

                Estudiante estudiante = new Estudiante(
                        resultado.getInt("id"),
                        resultado.getString("nombre"),
                        resultado.getString("rut"),
                        resultado.getString("curso"),
                        resultado.getString("correo"),
                        ""
                );

                estudiantes.add(estudiante);
            }

        } catch (SQLException e) {
            System.out.println("Error al consultar los estudiantes.");
            System.out.println(e.getMessage());
        }

        return estudiantes;
    }

    /**
     * Registra un estudiante en la base de datos.
     *
     * @param nombre nombre del estudiante
     * @param rut RUT del estudiante
     * @param curso curso del estudiante
     * @param correo correo del estudiante
     * @return true si se registra correctamente
     */
    public boolean guardar(String nombre, String rut,
                           String curso, String correo) {

        String sql = "INSERT INTO estudiantes "
                + "(nombre, rut, curso, correo) "
                + "VALUES (?, ?, ?, ?)";

        try (Connection conexion =
                     ConexionBD.getInstancia().obtenerConexion();
             PreparedStatement sentencia =
                     conexion.prepareStatement(sql)) {

            sentencia.setString(1, nombre);
            sentencia.setString(2, rut);
            sentencia.setString(3, curso);
            sentencia.setString(4, correo);

            return sentencia.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al registrar el estudiante.");
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * Actualiza los datos de un estudiante.
     *
     * @param idEstudiante identificador del estudiante
     * @param nombre nuevo nombre del estudiante
     * @param curso nuevo curso del estudiante
     * @param correo nuevo correo del estudiante
     * @return true si se actualiza correctamente
     */
    public boolean actualizar(int idEstudiante, String nombre,
                              String curso, String correo) {

        String sql = "UPDATE estudiantes "
                + "SET nombre = ?, curso = ?, correo = ? "
                + "WHERE id = ?";

        try (Connection conexion =
                     ConexionBD.getInstancia().obtenerConexion();
             PreparedStatement sentencia =
                     conexion.prepareStatement(sql)) {

            sentencia.setString(1, nombre);
            sentencia.setString(2, curso);
            sentencia.setString(3, correo);
            sentencia.setInt(4, idEstudiante);

            return sentencia.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al actualizar el estudiante.");
            System.out.println(e.getMessage());
            return false;
        }
    }
}
