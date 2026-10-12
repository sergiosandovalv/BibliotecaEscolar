
package datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Gestiona la conexion a la base de datos biblioteca.
 * Aplica el patron de diseno Singleton.
 *
 * @author Sergio Sandoval
 */
public class ConexionBD {

    private static ConexionBD instancia;

    private static final String URL =
            "jdbc:mysql://localhost:3306/biblioteca";

    private static final String USUARIO = "root";

    private ConexionBD() {
    }

    /**
     * Obtiene la unica instancia de ConexionBD.
     *
     * @return instancia de ConexionBD
     */
    public static synchronized ConexionBD getInstancia() {
        if (instancia == null) {
            instancia = new ConexionBD();
        }
        return instancia;
    }

    /**
     * Abre una conexion con MySQL.
     *
     * @return conexion activa
     * @throws SQLException si ocurre un error de conexion
     */
    public Connection obtenerConexion() throws SQLException {

        String password = System.getenv("MYSQL_PASSWORD");

        if (password == null || password.isEmpty()) {
            throw new SQLException(
                    "No esta configurada la variable MYSQL_PASSWORD."
            );
        }

        return DriverManager.getConnection(
                URL, USUARIO, password
        );
    }
}
