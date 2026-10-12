
package vista;

import controlador.BibliotecaControlador;
import modelo.Libro;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.GridLayout;

/**
 * Ventana para registrar libros en la Biblioteca Escolar.
 *
 * @author Sergio Sandoval
 */
public class VentanaRegistroLibro extends JFrame {

    private final BibliotecaControlador controlador;

    private final JTextField campoTitulo;
    private final JTextField campoAutor;
    private final JTextField campoIsbn;
    private final JTextField campoEditorial;
    private final JTextField campoStock;
    private final JTextField campoCategoria;

    /**
     * Inicializa la ventana de registro de libros.
     */
    public VentanaRegistroLibro() {

        controlador = new BibliotecaControlador();

        setTitle("Biblioteca Escolar - Registrar libro");
        setSize(500, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(7, 2, 10, 10));

        campoTitulo = new JTextField();
        campoAutor = new JTextField();
        campoIsbn = new JTextField();
        campoEditorial = new JTextField();
        campoStock = new JTextField();
        campoCategoria = new JTextField();

        panel.add(new JLabel("Titulo:"));
        panel.add(campoTitulo);

        panel.add(new JLabel("Autor:"));
        panel.add(campoAutor);

        panel.add(new JLabel("ISBN:"));
        panel.add(campoIsbn);

        panel.add(new JLabel("Editorial:"));
        panel.add(campoEditorial);

        panel.add(new JLabel("Stock:"));
        panel.add(campoStock);

        panel.add(new JLabel("ID categoria:"));
        panel.add(campoCategoria);

        JButton botonGuardar = new JButton("Guardar");
        JButton botonCancelar = new JButton("Cancelar");

        botonGuardar.addActionListener(e -> registrarLibro());
        botonCancelar.addActionListener(e -> dispose());

        panel.add(botonGuardar);
        panel.add(botonCancelar);

        add(panel);
    }

    /**
     * Valida los datos y registra un libro en MySQL.
     */
    private void registrarLibro() {

        String titulo = campoTitulo.getText().trim();
        String autor = campoAutor.getText().trim();
        String isbn = campoIsbn.getText().trim();
        String editorial = campoEditorial.getText().trim();

        if (titulo.isEmpty() || autor.isEmpty()
                || isbn.isEmpty() || editorial.isEmpty()
                || campoStock.getText().trim().isEmpty()
                || campoCategoria.getText().trim().isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Debe completar todos los campos.",
                    "Validacion",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        try {

            int stock = Integer.parseInt(
                    campoStock.getText().trim());

            int idCategoria = Integer.parseInt(
                    campoCategoria.getText().trim());

            if (stock < 0 || idCategoria <= 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "El stock no puede ser negativo "
                                + "y la categoria debe ser mayor que cero.",
                        "Validacion",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            Libro libro = new Libro(
                    0,
                    titulo,
                    autor,
                    isbn,
                    editorial,
                    stock,
                    idCategoria
            );

            boolean registrado =
                    controlador.registrarLibro(libro);

            if (registrado) {

                JOptionPane.showMessageDialog(
                        this,
                        "Libro registrado correctamente.",
                        "Biblioteca Escolar",
                        JOptionPane.INFORMATION_MESSAGE
                );

                dispose();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "No fue posible registrar el libro. "
                                + "Verifique el ISBN y la categoria.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "El stock y el ID de categoria "
                            + "deben ser numeros enteros.",
                    "Validacion",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }
}
