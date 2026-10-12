
package main;

import controlador.BibliotecaControlador;
import datos.ConexionBD;
import modelo.Estudiante;
import modelo.Libro;
import modelo.Prestamo;
import vista.VentanaPrincipal;

import javax.swing.SwingUtilities;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Clase principal del sistema BibliotecaEscolar.
 * Utiliza el controlador para consultar estudiantes,
 * libros y prestamos almacenados en MySQL.
 * Inicia la interfaz grafica del sistema.
 *
 * @author Sergio Sandoval
 */
public class Main {

    public static void main(String[] args) {

        System.out.println("==================================");
        System.out.println("       BIBLIOTECA ESCOLAR");
        System.out.println("==================================");

        try (Connection conexion =
                     ConexionBD.getInstancia().obtenerConexion()) {

            System.out.println("Conexion a MySQL: OK");
            System.out.println("Base de datos: biblioteca");

            BibliotecaControlador controlador =
                    new BibliotecaControlador();

            List<Estudiante> estudiantes =
                    controlador.listarEstudiantes();

            System.out.println();
            System.out.println("========= LISTADO DE ESTUDIANTES =========");

            for (Estudiante estudiante : estudiantes) {
                System.out.println(
                        "ID: " + estudiante.getId()
                                + " | Nombre: " + estudiante.getNombre()
                                + " | RUT: " + estudiante.getRut()
                                + " | Correo: " + estudiante.getCorreo()
                );
            }

            System.out.println("----------------------------------------");
            System.out.println("Total de estudiantes: " + estudiantes.size());

            List<Libro> libros = controlador.listarLibros();

            System.out.println();
            System.out.println("========== LISTADO DE LIBROS ==========");

            for (Libro libro : libros) {
                System.out.println(
                        "ID: " + libro.getId()
                                + " | Titulo: " + libro.getTitulo()
                                + " | Autor: " + libro.getAutor()
                                + " | Stock: " + libro.getStock()
                );
            }

            System.out.println("---------------------------------------");
            System.out.println("Total de libros: " + libros.size());

            List<Prestamo> prestamos =
                    controlador.listarPrestamos();

            System.out.println();
            System.out.println("========= LISTADO DE PRESTAMOS =========");

            for (Prestamo prestamo : prestamos) {
                System.out.println(
                        "ID: " + prestamo.getId()
                                + " | Estudiante: " + prestamo.getIdEstudiante()
                                + " | Libro: " + prestamo.getIdLibro()
                                + " | Fecha prestamo: " + prestamo.getFechaPrestamo()
                                + " | Fecha devolucion: " + prestamo.getFechaDevolucion()
                                + " | Devuelto: "
                                + (prestamo.isDevuelto() ? "SI" : "NO")
                );
            }

            System.out.println("----------------------------------------");
            System.out.println("Total de prestamos: " + prestamos.size());

            SwingUtilities.invokeLater(() -> {
                VentanaPrincipal ventana = new VentanaPrincipal();
                ventana.setVisible(true);
            });

        } catch (SQLException e) {
            System.out.println("Conexion a MySQL: ERROR");
            System.out.println(e.getMessage());
        }
    }
}
