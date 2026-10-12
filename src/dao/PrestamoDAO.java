
package dao;

import datos.ConexionBD;
import modelo.Prestamo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Gestiona los prestamos y devoluciones
 * de la Biblioteca Escolar.
 *
 * @author Sergio Sandoval
 */
public class PrestamoDAO {

    /**
     * Obtiene todos los prestamos registrados.
     *
     * @return lista de prestamos
     */
    public List<Prestamo> listarTodos() {

        List<Prestamo> prestamos = new ArrayList<>();

        String sql = "SELECT id, id_estudiante, id_libro, "
                + "fecha_prestamo, fecha_devolucion, devuelto "
                + "FROM prestamos ORDER BY id";

        try (Connection conexion =
                     ConexionBD.getInstancia().obtenerConexion();
             PreparedStatement sentencia =
                     conexion.prepareStatement(sql);
             ResultSet resultado = sentencia.executeQuery()) {

            while (resultado.next()) {

                Prestamo prestamo = new Prestamo(
                        resultado.getInt("id"),
                        resultado.getInt("id_estudiante"),
                        resultado.getInt("id_libro"),
                        resultado.getDate("fecha_prestamo").toLocalDate(),
                        resultado.getDate("fecha_devolucion").toLocalDate(),
                        resultado.getBoolean("devuelto")
                );

                prestamos.add(prestamo);
            }

        } catch (SQLException e) {
            System.out.println("Error al consultar los prestamos.");
            System.out.println(e.getMessage());
        }

        return prestamos;
    }

    /**
     * Registra un prestamo y descuenta el stock.
     *
     * @param prestamo prestamo que se desea registrar
     * @return true si se registra correctamente
     */
    public synchronized boolean guardar(Prestamo prestamo) {

        String sqlStock = "SELECT stock FROM libros "
                + "WHERE id = ? FOR UPDATE";

        String sqlPrestamo = "INSERT INTO prestamos "
                + "(id_estudiante, id_libro, fecha_prestamo, "
                + "fecha_devolucion, devuelto) "
                + "VALUES (?, ?, ?, ?, ?)";

        String sqlActualizar = "UPDATE libros "
                + "SET stock = stock - 1 WHERE id = ?";

        try (Connection conexion =
                     ConexionBD.getInstancia().obtenerConexion()) {

            conexion.setAutoCommit(false);

            try {
                int stock;

                try (PreparedStatement sentencia =
                             conexion.prepareStatement(sqlStock)) {

                    sentencia.setInt(1, prestamo.getIdLibro());

                    try (ResultSet resultado =
                                 sentencia.executeQuery()) {

                        if (!resultado.next()) {
                            conexion.rollback();
                            return false;
                        }

                        stock = resultado.getInt("stock");
                    }
                }

                if (stock <= 0) {
                    System.out.println("No hay stock disponible.");
                    conexion.rollback();
                    return false;
                }

                try (PreparedStatement sentencia =
                             conexion.prepareStatement(sqlPrestamo)) {

                    sentencia.setInt(1, prestamo.getIdEstudiante());
                    sentencia.setInt(2, prestamo.getIdLibro());
                    sentencia.setDate(3,
                            java.sql.Date.valueOf(
                                    prestamo.getFechaPrestamo()));
                    sentencia.setDate(4,
                            java.sql.Date.valueOf(
                                    prestamo.getFechaDevolucion()));
                    sentencia.setBoolean(5, prestamo.isDevuelto());

                    sentencia.executeUpdate();
                }

                try (PreparedStatement sentencia =
                             conexion.prepareStatement(sqlActualizar)) {

                    sentencia.setInt(1, prestamo.getIdLibro());
                    sentencia.executeUpdate();
                }

                conexion.commit();
                return true;

            } catch (SQLException e) {
                conexion.rollback();
                throw e;
            }

        } catch (SQLException e) {
            System.out.println("Error al guardar el prestamo.");
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * Registra una devolucion y recupera el stock.
     *
     * @param idPrestamo identificador del prestamo
     * @return true si se registra correctamente
     */
    public synchronized boolean registrarDevolucion(int idPrestamo) {

        String sqlConsultar = "SELECT id_libro, devuelto "
                + "FROM prestamos WHERE id = ? FOR UPDATE";

        String sqlDevolucion = "UPDATE prestamos "
                + "SET devuelto = TRUE WHERE id = ?";

        String sqlStock = "UPDATE libros "
                + "SET stock = stock + 1 WHERE id = ?";

        try (Connection conexion =
                     ConexionBD.getInstancia().obtenerConexion()) {

            conexion.setAutoCommit(false);

            try {
                int idLibro;

                try (PreparedStatement sentencia =
                             conexion.prepareStatement(sqlConsultar)) {

                    sentencia.setInt(1, idPrestamo);

                    try (ResultSet resultado =
                                 sentencia.executeQuery()) {

                        if (!resultado.next()
                                || resultado.getBoolean("devuelto")) {

                            conexion.rollback();
                            return false;
                        }

                        idLibro = resultado.getInt("id_libro");
                    }
                }

                try (PreparedStatement sentencia =
                             conexion.prepareStatement(sqlDevolucion)) {

                    sentencia.setInt(1, idPrestamo);
                    sentencia.executeUpdate();
                }

                try (PreparedStatement sentencia =
                             conexion.prepareStatement(sqlStock)) {

                    sentencia.setInt(1, idLibro);
                    sentencia.executeUpdate();
                }

                conexion.commit();
                return true;

            } catch (SQLException e) {
                conexion.rollback();
                throw e;
            }

        } catch (SQLException e) {
            System.out.println("Error al registrar la devolucion.");
            System.out.println(e.getMessage());
            return false;
        }
    }
}
