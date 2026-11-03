package edu.programacion.ejercicio2506;

// Excepción que indica que se superó la cantidad máxima de extracciones.
public class SuperaCantidadExtraccionesException extends Exception {

    public SuperaCantidadExtraccionesException() {
        super("Se superó la cantidad máxima de extracciones permitidas.");
    }
}