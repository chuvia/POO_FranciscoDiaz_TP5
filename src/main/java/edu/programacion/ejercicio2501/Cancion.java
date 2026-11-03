package edu.programacion.ejercicio2501;

public class Cancion implements Reproducible {
    private String nombre;

    public Cancion(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void play() {
        System.out.println("Reproduciendo canción: " + nombre);
    }

    @Override
    public void bucle() {
        System.out.println("Canción en bucle: " + nombre);
    }

    @Override
    public void stop() {
        System.out.println("Canción detenida: " + nombre);
    }

    @Override
    public String toString() {
        return "Canción: " + nombre;
    }
}
