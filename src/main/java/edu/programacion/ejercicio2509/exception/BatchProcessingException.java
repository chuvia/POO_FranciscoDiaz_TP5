
package edu.programacion.ejercicio2509.exception;

public class BatchProcessingException extends Exception {

    private int numeroLinea;

    // Constructor para errores generales.
    public BatchProcessingException(String mensaje, Throwable causa) {
        super(mensaje, causa);
        this.numeroLinea = -1;
    }

    // Constructor para errores que ocurren en una línea específica del CSV.
    public BatchProcessingException(String mensaje, int numeroLinea, Throwable causa) {
        super(mensaje, causa);
        this.numeroLinea = numeroLinea;
    }

    public int getNumeroLinea() {
        return numeroLinea;
    }
}
