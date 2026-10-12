
package vista;

import controlador.BibliotecaControlador;
import modelo.Libro;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Permite modificar los libros registrados.
 *
 * @author Sergio Sandoval
 */
public class VentanaEditarLibro extends JFrame {

    private final BibliotecaControlador controlador;
    private final JComboBox<Libro> comboLibros;
    private final JTextField campoTitulo;
    private final JTextField campoAutor;
    private final JTextField campoIsbn;
    private final JTextField campoEditorial;
    private final JTextField campoStock;
    private final JTextField campoCategoria;

    public VentanaEditarLibro() {

        controlador = new BibliotecaControlador();

        setTitle("Biblioteca Escolar - Editar libro");
        setSize(550, 430);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(8, 2, 10, 10));

        comboLibros = new JComboBox<>();
        campoTitulo = new JTextField();
        campoAutor = new JTextField();
        campoIsbn = new JTextField();
        campoEditorial = new JTextField();
        campoStock = new JTextField();
        campoCategoria = new JTextField();

        panel.add(new JLabel("Libro:"));
        panel.add(comboLibros);
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

        JButton botonGuardar = new JButton("Guardar cambios");
        JButton botonCancelar = new JButton("Cancelar");

        panel.add(botonGuardar);
        panel.add(botonCancelar);

        add(panel);

        comboLibros.addActionListener(e -> mostrarLibro());

        botonGuardar.addActionListener(e -> guardarCambios());
        botonCancelar.addActionListener(e -> dispose());

        cargarLibros();
    }

    /**
     * Carga los libros desde MySQL.
     */
    private void cargarLibros() {

        List<Libro> libros = controlador.listarLibros();

        comboLibros.removeAllItems();

        for (Libro libro : libros) {
            comboLibros.addItem(libro);
        }

        mostrarLibro();
    }

    /**
     * Muestra los datos del libro seleccionado.
     */
    private void mostrarLibro() {

        Libro libro = (Libro) comboLibros.getSelectedItem();

        if (libro == null) {
            return;
        }

        campoTitulo.setText(libro.getTitulo());
        campoAutor.setText(libro.getAutor());
        campoIsbn.setText(libro.getIsbn());
        campoEditorial.setText(libro.getEditorial());
        campoStock.setText(String.valueOf(libro.getStock()));
        campoCategoria.setText(String.valueOf(libro.getIdCategoria()));
    }

    /**
     * Actualiza los datos del libro seleccionado.
     */
    private void guardarCambios() {

        Libro libro = (Libro) comboLibros.getSelectedItem();

        if (libro == null) {
            JOptionPane.showMessageDialog(this,
                    "Seleccione un libro.");
            return;
        }

        try {

            String titulo = campoTitulo.getText().trim();
            String autor = campoAutor.getText().trim();
            String isbn = campoIsbn.getText().trim();
            String editorial = campoEditorial.getText().trim();

            int stock = Integer.parseInt(campoStock.getText().trim());
            int categoria = Integer.parseInt(
                    campoCategoria.getText().trim());

            if (titulo.isEmpty() || autor.isEmpty()
                    || isbn.isEmpty() || editorial.isEmpty()
                    || stock < 0 || categoria <= 0) {

                JOptionPane.showMessageDialog(this,
                        "Ingrese datos validos.");
                return;
            }

            Libro libroActualizado = new Libro(
                    libro.getId(),
                    titulo,
                    autor,
                    isbn,
                    editorial,
                    stock,
                    categoria
            );

            if (controlador.actualizarLibro(libroActualizado)) {

                JOptionPane.showMessageDialog(this,
                        "Libro actualizado correctamente.");

                cargarLibros();

            } else {

                JOptionPane.showMessageDialog(this,
                        "No fue posible actualizar el libro.");
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(this,
                    "El stock y la categoria deben ser numeros enteros.");
        }
    }
}
