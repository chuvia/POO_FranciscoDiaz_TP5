package edu.programacion.ejercicio2501;

public class Video implements Reproducible {
    private String nombre;

    public Video(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void play() {
        System.out.println("Reproduciendo video: " + nombre);
    }

    @Override
    public void bucle() {
        System.out.println("Video en bucle: " + nombre);
    }

    @Override
    public void stop() {
        System.out.println("Video detenido: " + nombre);
    }

    @Override
    public String toString() {
        return "Video: " + nombre;
    }
}