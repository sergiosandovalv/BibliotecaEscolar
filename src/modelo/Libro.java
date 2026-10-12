
package modelo;

/**
 * Representa un libro de la Biblioteca Escolar.
 * Contiene los datos asociados a la tabla libros.
 *
 * @author Sergio Sandoval
 */
public class Libro {

    private int id;
    private String titulo;
    private String autor;
    private String isbn;
    private String editorial;
    private int stock;
    private int idCategoria;

    /**
     * Constructor de Libro.
     *
     * @param id identificador del libro
     * @param titulo titulo del libro
     * @param autor autor del libro
     * @param isbn codigo ISBN
     * @param editorial editorial del libro
     * @param stock cantidad disponible
     * @param idCategoria identificador de la categoria
     */
    public Libro(int id, String titulo, String autor,
                 String isbn, String editorial,
                 int stock, int idCategoria) {

        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.editorial = editorial;
        this.stock = stock;
        this.idCategoria = idCategoria;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    /**
     * Devuelve una descripcion del libro.
     *
     * @return titulo y autor del libro
     */
    @Override
    public String toString() {
        return titulo + " - " + autor;
    }
}
