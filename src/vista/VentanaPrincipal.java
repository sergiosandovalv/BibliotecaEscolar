
package vista;

import controlador.BibliotecaControlador;
import modelo.Estudiante;
import modelo.Libro;
import modelo.Prestamo;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.List;

/**
 * Ventana principal del sistema BibliotecaEscolar.
 * Permite consultar estudiantes, libros y prestamos,
 * registrar estudiantes, libros, prestamos y devoluciones,
 * editar libros, actualizar registros y salir.
 *
 * @author Sergio Sandoval
 */
public class VentanaPrincipal extends JFrame {

    private final BibliotecaControlador controlador;
    private final DefaultTableModel modeloTabla;

    private String ultimaConsulta = "";

    /**
     * Inicializa la ventana principal.
     */
    public VentanaPrincipal() {

        controlador = new BibliotecaControlador();

        setTitle("Biblioteca Escolar");
        setSize(1050, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(2, 4, 5, 5));

        JButton botonEstudiantes =
                new JButton("Consultar estudiantes");

        JButton botonLibros =
                new JButton("Consultar libros");

        JButton botonPrestamos =
                new JButton("Consultar prestamos");

        JButton botonRegistrarEstudiante =
                new JButton("Registrar estudiante");

        JButton botonRegistrarPrestamo =
                new JButton("Registrar prestamo");

        JButton botonRegistrarDevolucion =
                new JButton("Registrar devolucion");

        JButton botonRegistrarLibro =
                new JButton("Registrar libro");

        JButton botonEditarLibro =
                new JButton("Editar libro");

        botonEstudiantes.addActionListener(e ->
                consultarEstudiantes());

        botonLibros.addActionListener(e ->
                consultarLibros());

        botonPrestamos.addActionListener(e ->
                consultarPrestamos());

        botonRegistrarEstudiante.addActionListener(e ->
                registrarEstudiante());

        botonRegistrarPrestamo.addActionListener(e ->
                registrarPrestamo());

        botonRegistrarDevolucion.addActionListener(e ->
                registrarDevolucion());

        botonRegistrarLibro.addActionListener(e ->
                registrarLibro());

        botonEditarLibro.addActionListener(e ->
                editarLibro());

        panel.add(botonEstudiantes);
        panel.add(botonLibros);
        panel.add(botonPrestamos);
        panel.add(botonRegistrarEstudiante);
        panel.add(botonRegistrarPrestamo);
        panel.add(botonRegistrarDevolucion);
        panel.add(botonRegistrarLibro);
        panel.add(botonEditarLibro);

        add(panel, BorderLayout.NORTH);

        modeloTabla = new DefaultTableModel() {

            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };

        JTable tabla = new JTable(modeloTabla);

        JScrollPane panelDesplazamiento =
                new JScrollPane(tabla);

        add(panelDesplazamiento, BorderLayout.CENTER);

        // Botones inferiores.
        JPanel panelInferior =
                new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5));

        JButton botonActualizar =
                new JButton("Actualizar");

        JButton botonSalir =
                new JButton("Salir");

        botonActualizar.addActionListener(e ->
                actualizarTabla());

        botonSalir.addActionListener(e ->
                salirAplicacion());

        panelInferior.add(botonActualizar);
        panelInferior.add(botonSalir);

        add(panelInferior, BorderLayout.SOUTH);
    }

    /**
     * Abre la ventana de registro de estudiantes.
     */
    private void registrarEstudiante() {

        VentanaRegistroEstudiante ventana =
                new VentanaRegistroEstudiante();

        ventana.setVisible(true);
    }

    /**
     * Abre la ventana de registro de prestamos.
     */
    private void registrarPrestamo() {

        VentanaRegistroPrestamo ventana =
                new VentanaRegistroPrestamo();

        ventana.setVisible(true);
    }

    /**
     * Abre la ventana de registro de devoluciones.
     */
    private void registrarDevolucion() {

        VentanaRegistroDevolucion ventana =
                new VentanaRegistroDevolucion();

        ventana.setVisible(true);
    }

    /**
     * Abre la ventana de registro de libros.
     */
    private void registrarLibro() {

        VentanaRegistroLibro ventana =
                new VentanaRegistroLibro();

        ventana.setVisible(true);
    }

    /**
     * Abre la ventana de edicion de libros.
     */
    private void editarLibro() {

        VentanaEditarLibro ventana =
                new VentanaEditarLibro();

        ventana.setVisible(true);
    }

    /**
     * Actualiza los registros de la ultima consulta.
     */
    private void actualizarTabla() {

        switch (ultimaConsulta) {

            case "estudiantes":
                mostrarEstudiantes(false);
                break;

            case "libros":
                mostrarLibros(false);
                break;

            case "prestamos":
                mostrarPrestamos(false);
                break;

            default:
                JOptionPane.showMessageDialog(
                        this,
                        "Primero seleccione que registros desea consultar.",
                        "Biblioteca Escolar",
                        JOptionPane.INFORMATION_MESSAGE
                );
                break;
        }
    }

    /**
     * Solicita confirmacion antes de salir.
     */
    private void salirAplicacion() {

        int opcion = JOptionPane.showConfirmDialog(
                this,
                "¿Desea salir de Biblioteca Escolar?",
                "Confirmar salida",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );

        if (opcion == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    /**
     * Consulta los estudiantes registrados.
     */
    private void consultarEstudiantes() {
        mostrarEstudiantes(true);
    }

    /**
     * Muestra los estudiantes desde MySQL.
     *
     * @param mostrarMensaje indica si muestra el total
     */
    private void mostrarEstudiantes(boolean mostrarMensaje) {

        List<Estudiante> estudiantes =
                controlador.listarEstudiantes();

        modeloTabla.setRowCount(0);
        modeloTabla.setColumnCount(0);

        modeloTabla.addColumn("ID");
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("RUT");
        modeloTabla.addColumn("Correo");

        for (Estudiante estudiante : estudiantes) {

            Object[] fila = {
                    estudiante.getId(),
                    estudiante.getNombre(),
                    estudiante.getRut(),
                    estudiante.getCorreo()
            };

            modeloTabla.addRow(fila);
        }

        ultimaConsulta = "estudiantes";

        if (mostrarMensaje) {
            JOptionPane.showMessageDialog(
                    this,
                    "Estudiantes registrados: " + estudiantes.size(),
                    "Biblioteca Escolar",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }
    }

    /**
     * Consulta los libros registrados.
     */
    private void consultarLibros() {
        mostrarLibros(true);
    }

    /**
     * Muestra los libros desde MySQL.
     *
     * @param mostrarMensaje indica si muestra el total
     */
    private void mostrarLibros(boolean mostrarMensaje) {

        List<Libro> libros =
                controlador.listarLibros();

        modeloTabla.setRowCount(0);
        modeloTabla.setColumnCount(0);

        modeloTabla.addColumn("ID");
        modeloTabla.addColumn("Titulo");
        modeloTabla.addColumn("Autor");
        modeloTabla.addColumn("Stock");

        for (Libro libro : libros) {

            Object[] fila = {
                    libro.getId(),
                    libro.getTitulo(),
                    libro.getAutor(),
                    libro.getStock()
            };

            modeloTabla.addRow(fila);
        }

        ultimaConsulta = "libros";

        if (mostrarMensaje) {
            JOptionPane.showMessageDialog(
                    this,
                    "Libros registrados: " + libros.size(),
                    "Biblioteca Escolar",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }
    }

    /**
     * Consulta los prestamos registrados.
     */
    private void consultarPrestamos() {
        mostrarPrestamos(true);
    }

    /**
     * Muestra los prestamos desde MySQL.
     *
     * @param mostrarMensaje indica si muestra el total
     */
    private void mostrarPrestamos(boolean mostrarMensaje) {

        List<Prestamo> prestamos =
                controlador.listarPrestamos();

        modeloTabla.setRowCount(0);
        modeloTabla.setColumnCount(0);

        modeloTabla.addColumn("ID");
        modeloTabla.addColumn("Estudiante");
        modeloTabla.addColumn("Libro");
        modeloTabla.addColumn("Fecha prestamo");
        modeloTabla.addColumn("Fecha devolucion");
        modeloTabla.addColumn("Devuelto");

        for (Prestamo prestamo : prestamos) {

            Object[] fila = {
                    prestamo.getId(),
                    prestamo.getIdEstudiante(),
                    prestamo.getIdLibro(),
                    prestamo.getFechaPrestamo(),
                    prestamo.getFechaDevolucion(),
                    prestamo.isDevuelto() ? "SI" : "NO"
            };

            modeloTabla.addRow(fila);
        }

        ultimaConsulta = "prestamos";

        if (mostrarMensaje) {
            JOptionPane.showMessageDialog(
                    this,
                    "Prestamos registrados: " + prestamos.size(),
                    "Biblioteca Escolar",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }
    }
}
