package edu.programacion.ejercicio2504;

public class NoListaException extends Exception {

    private String nombre;
    private Long cantidadTrabajos;

    public NoListaException(String nombre, Long trabajos) {
        this.nombre = nombre;
        this.cantidadTrabajos = trabajos;
    }

    @Override
    public String getMessage() {
        return "La Cola de Trabajo: " + nombre
                + " no está disponible. Cantidad de trabajos a procesar: "
                + cantidadTrabajos;
    }
}