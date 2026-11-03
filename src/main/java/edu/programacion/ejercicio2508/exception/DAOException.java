package edu.programacion.ejercicio2508.exception;

// Excepción personalizada para los errores de acceso a datos.
public class DAOException extends Exception {

    public DAOException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}