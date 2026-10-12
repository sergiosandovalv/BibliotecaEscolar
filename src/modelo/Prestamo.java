
package modelo;

import java.time.LocalDate;

/**
 * Representa un prestamo de libros
 * en el sistema Biblioteca Escolar.
 *
 * @author Sergio Sandoval
 */
public class Prestamo {

    private int id;
    private int idEstudiante;
    private int idLibro;
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucion;
    private boolean devuelto;

    /**
     * Constructor de Prestamo.
     *
     * @param id identificador del prestamo
     * @param idEstudiante identificador del estudiante
     * @param idLibro identificador del libro
     * @param fechaPrestamo fecha del prestamo
     * @param fechaDevolucion fecha de devolucion
     * @param devuelto indica si el libro fue devuelto
     */
    public Prestamo(int id, int idEstudiante, int idLibro,
                    LocalDate fechaPrestamo,
                    LocalDate fechaDevolucion,
                    boolean devuelto) {

        this.id = id;
        this.idEstudiante = idEstudiante;
        this.idLibro = idLibro;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        this.devuelto = devuelto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(int idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public int getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
    }

    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(LocalDate fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(LocalDate fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public boolean isDevuelto() {
        return devuelto;
    }

    public void setDevuelto(boolean devuelto) {
        this.devuelto = devuelto;
    }
}
