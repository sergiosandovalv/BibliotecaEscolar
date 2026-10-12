
package vista;

import controlador.BibliotecaControlador;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.GridLayout;

/**
 * Ventana para registrar devoluciones de libros.
 *
 * @author Sergio Sandoval
 */
public class VentanaRegistroDevolucion extends JFrame {

    private final BibliotecaControlador controlador;
    private final JTextField campoIdPrestamo;

    public VentanaRegistroDevolucion() {

        controlador = new BibliotecaControlador();

        setTitle("Biblioteca Escolar - Registrar devolucion");
        setSize(450, 180);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));

        panel.add(new JLabel("ID prestamo:"));

        campoIdPrestamo = new JTextField();
        panel.add(campoIdPrestamo);

        JButton botonGuardar = new JButton("Registrar devolucion");
        JButton botonCancelar = new JButton("Cancelar");

        botonGuardar.addActionListener(e -> registrarDevolucion());
        botonCancelar.addActionListener(e -> dispose());

        panel.add(botonGuardar);
        panel.add(botonCancelar);

        add(panel);
    }

    /**
     * Registra la devolucion mediante el controlador.
     */
    private void registrarDevolucion() {

        try {
            int idPrestamo =
                    Integer.parseInt(campoIdPrestamo.getText().trim());

            if (idPrestamo <= 0) {
                JOptionPane.showMessageDialog(
                        this,
                        "Ingrese un ID de prestamo valido.",
                        "Validacion",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            boolean registrado =
                    controlador.registrarDevolucion(idPrestamo);

            if (registrado) {
                JOptionPane.showMessageDialog(
                        this,
                        "Devolucion registrada correctamente.",
                        "Biblioteca Escolar",
                        JOptionPane.INFORMATION_MESSAGE
                );
                dispose();
            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "No fue posible registrar la devolucion. "
                                + "Verifique el ID o si ya fue devuelto.",
                        "Biblioteca Escolar",
                        JOptionPane.WARNING_MESSAGE
                );
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(
                    this,
                    "El ID debe ser un numero entero.",
                    "Validacion",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }
}
