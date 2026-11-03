package edu.programacion.ejercicio2507.exception;

// Capa de Excepciones (exception).
// Encapsula los errores que pueden producirse durante la auditoría.
public class AuditoriaException extends Exception {

    public AuditoriaException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}