
package modelo;

/**
 * Representa una categoria de libros
 * de la Biblioteca Escolar.
 *
 * @author Sergio Sandoval
 */
public class Categoria {

    private int id;
    private String nombre;

    /**
     * Constructor de Categoria.
     *
     * @param id identificador de la categoria
     * @param nombre nombre de la categoria
     */
    public Categoria(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Devuelve el nombre de la categoria.
     *
     * @return nombre de la categoria
     */
    @Override
    public String toString() {
        return nombre;
    }
}
