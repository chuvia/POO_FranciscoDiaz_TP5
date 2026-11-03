package edu.programacion.ejercicio2504;

public class SinTrabajoEnColaException extends Exception {

    private String nombre;

    public SinTrabajoEnColaException(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String getMessage() {
        return "La cola " + nombre + " no tiene trabajos para procesar.";
    }
}
