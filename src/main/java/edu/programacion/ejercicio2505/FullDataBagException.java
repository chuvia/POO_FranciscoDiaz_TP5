package edu.programacion.ejercicio2505;

// Excepción personalizada para indicar que la bolsa está llena.
public class FullDataBagException extends Exception {

    public FullDataBagException() {
        super("La bolsa está llena. No se pueden agregar más elementos.");
    }
}
