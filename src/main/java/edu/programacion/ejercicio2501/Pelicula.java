package edu.programacion.ejercicio2501;

public class Pelicula implements Reproducible {
    private String nombre;

    public Pelicula(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void play() {
        System.out.println("Reproduciendo película: " + nombre);
    }

    @Override
    public void bucle() {
        System.out.println("Película en bucle: " + nombre);
    }

    @Override
    public void stop() {
        System.out.println("Película detenida: " + nombre);
    }

    @Override
    public String toString() {
        return "Película: " + nombre;
    }
}
