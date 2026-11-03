package edu.programacion.ejercicio2505;

// Excepción personalizada para indicar que la bolsa está vacía.
public class EmptyDataBagException extends Exception {

    public EmptyDataBagException() {
        super("La bolsa está vacía. No hay elementos para eliminar.");
    }
}
