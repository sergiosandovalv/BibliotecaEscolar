
package vista;

import controlador.BibliotecaControlador;
import modelo.Prestamo;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import java.awt.GridLayout;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Ventana para registrar prestamos de libros.
 * Utiliza el controlador para guardar los datos en MySQL.
 * Ejecuta el registro en un hilo independiente.
 *
 * @author Sergio Sandoval
 */
public class VentanaRegistroPrestamo extends JFrame {

    private final BibliotecaControlador controlador;

    private final JTextField campoEstudiante;
    private final JTextField campoLibro;
    private final JTextField campoFechaPrestamo;
    private final JTextField campoFechaDevolucion;

    private final JButton botonGuardar;

    /**
     * Inicializa la ventana de registro.
     */
    public VentanaRegistroPrestamo() {

        controlador = new BibliotecaControlador();

        setTitle("Biblioteca Escolar - Registrar prestamo");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));

        campoEstudiante = new JTextField();
        campoLibro = new JTextField();

        campoFechaPrestamo = new JTextField(
                LocalDate.now().toString());

        campoFechaDevolucion = new JTextField(
                LocalDate.now().plusDays(7).toString());

        panel.add(new JLabel("ID estudiante:"));
        panel.add(campoEstudiante);

        panel.add(new JLabel("ID libro:"));
        panel.add(campoLibro);

        panel.add(new JLabel("Fecha prestamo (AAAA-MM-DD):"));
        panel.add(campoFechaPrestamo);

        panel.add(new JLabel("Fecha devolucion (AAAA-MM-DD):"));
        panel.add(campoFechaDevolucion);

        botonGuardar = new JButton("Guardar");
        JButton botonCancelar = new JButton("Cancelar");

        botonGuardar.addActionListener(e ->
                registrarPrestamo());

        botonCancelar.addActionListener(e ->
                dispose());

        panel.add(botonGuardar);
        panel.add(botonCancelar);

        add(panel);
    }

    /**
     * Valida los datos y registra el prestamo.
     */
    private void registrarPrestamo() {

        if (campoEstudiante.getText().trim().isEmpty()
                || campoLibro.getText().trim().isEmpty()
                || campoFechaPrestamo.getText().trim().isEmpty()
                || campoFechaDevolucion.getText().trim().isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Debe completar todos los campos.",
                    "Validacion",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        try {
            int idEstudiante = Integer.parseInt(
                    campoEstudiante.getText().trim());

            int idLibro = Integer.parseInt(
                    campoLibro.getText().trim());

            LocalDate fechaPrestamo = LocalDate.parse(
                    campoFechaPrestamo.getText().trim());

            LocalDate fechaDevolucion = LocalDate.parse(
                    campoFechaDevolucion.getText().trim());

            if (idEstudiante <= 0 || idLibro <= 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "Los identificadores deben ser mayores que cero.",
                        "Validacion",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            if (fechaDevolucion.isBefore(fechaPrestamo)) {

                JOptionPane.showMessageDialog(
                        this,
                        "La fecha de devolucion no puede ser anterior "
                                + "a la fecha del prestamo.",
                        "Validacion",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            Prestamo prestamo = new Prestamo(
                    0,
                    idEstudiante,
                    idLibro,
                    fechaPrestamo,
                    fechaDevolucion,
                    false
            );

            botonGuardar.setEnabled(false);

            Thread hiloRegistro = new Thread(() -> {

                boolean registrado =
                        controlador.registrarPrestamo(prestamo);

                SwingUtilities.invokeLater(() -> {

                    botonGuardar.setEnabled(true);

                    if (registrado) {

                        JOptionPane.showMessageDialog(
                                this,
                                "Prestamo registrado correctamente.",
                                "Biblioteca Escolar",
                                JOptionPane.INFORMATION_MESSAGE
                        );

                        dispose();

                    } else {

                        JOptionPane.showMessageDialog(
                                this,
                                "No fue posible registrar el prestamo. "
                                        + "Verifique el estudiante, el libro "
                                        + "y el stock disponible.",
                                "Error",
                                JOptionPane.ERROR_MESSAGE
                        );
                    }
                });
            });

            hiloRegistro.start();

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Los identificadores deben ser numeros enteros.",
                    "Validacion",
                    JOptionPane.WARNING_MESSAGE
            );

        } catch (DateTimeParseException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Las fechas deben tener el formato AAAA-MM-DD.",
                    "Validacion",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }
}
