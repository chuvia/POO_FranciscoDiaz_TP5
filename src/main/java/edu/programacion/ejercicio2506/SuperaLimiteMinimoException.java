package edu.programacion.ejercicio2506;

// Excepción que indica que se superó el límite mínimo de extracción.
public class SuperaLimiteMinimoException extends Exception {

    public SuperaLimiteMinimoException() {
        super("La extracción supera el límite mínimo permitido.");
    }
}