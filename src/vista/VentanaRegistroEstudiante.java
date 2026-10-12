
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
 * Ventana para registrar estudiantes
 * en el sistema BibliotecaEscolar.
 *
 * @author Sergio Sandoval
 */
public class VentanaRegistroEstudiante extends JFrame {

    private final BibliotecaControlador controlador;

    private final JTextField campoNombre;
    private final JTextField campoRut;
    private final JTextField campoCurso;
    private final JTextField campoCorreo;

    /**
     * Inicializa el formulario de registro.
     */
    public VentanaRegistroEstudiante() {

        controlador = new BibliotecaControlador();

        setTitle("Biblioteca Escolar - Registrar estudiante");
        setSize(450, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));

        campoNombre = new JTextField();
        campoRut = new JTextField();
        campoCurso = new JTextField();
        campoCorreo = new JTextField();

        panel.add(new JLabel("Nombre:"));
        panel.add(campoNombre);

        panel.add(new JLabel("RUT:"));
        panel.add(campoRut);

        panel.add(new JLabel("Curso:"));
        panel.add(campoCurso);

        panel.add(new JLabel("Correo:"));
        panel.add(campoCorreo);

        JButton botonGuardar = new JButton("Guardar");
        JButton botonCancelar = new JButton("Cancelar");

        botonGuardar.addActionListener(e -> registrarEstudiante());
        botonCancelar.addActionListener(e -> dispose());

        panel.add(botonGuardar);
        panel.add(botonCancelar);

        add(panel);
    }

    /**
     * Valida los campos y registra el estudiante
     * mediante el controlador.
     */
    private void registrarEstudiante() {

        String nombre = campoNombre.getText().trim();
        String rut = campoRut.getText().trim();
        String curso = campoCurso.getText().trim();
        String correo = campoCorreo.getText().trim();

        if (nombre.isEmpty() || rut.isEmpty()
                || curso.isEmpty() || correo.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Debe completar todos los campos.",
                    "Validacion",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        boolean registrado = controlador.registrarEstudiante(
                nombre, rut, curso, correo
        );

        if (registrado) {

            JOptionPane.showMessageDialog(
                    this,
                    "Estudiante registrado correctamente.",
                    "Biblioteca Escolar",
                    JOptionPane.INFORMATION_MESSAGE
            );

            dispose();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "No fue posible registrar el estudiante.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}
