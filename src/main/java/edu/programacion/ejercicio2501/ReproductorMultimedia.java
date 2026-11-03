package edu.programacion.ejercicio2501;

public class ReproductorMultimedia {
    private Reproducible contenido;
    private boolean reproduciendo;

    public ReproductorMultimedia() {
        this.contenido = null;
        this.reproduciendo = false;
    }

    public void cargar(Reproducible contenido) {
        if (this.reproduciendo) {
            this.contenido.stop();
        }

        this.contenido = contenido;
        this.reproduciendo = false;

        System.out.println("Contenido cargado: " + contenido);
    }

    public void play() {
        if (contenido != null) {
            contenido.play();
            reproduciendo = true;
        } else {
            System.out.println("No hay contenido cargado.");
        }
    }

    public void bucle() {
        if (contenido != null) {
            contenido.bucle();
            reproduciendo = true;
        } else {
            System.out.println("No hay contenido cargado.");
        }
    }

    public void stop() {
        if (contenido != null) {
            contenido.stop();
            reproduciendo = false;
        }
    }

    public void mostrarContenido() {
        System.out.println("Contenido actual: " + contenido);
        System.out.println(
                "Estado: " +
                        (reproduciendo ? "Reproduciendo" : "En pausa")
        );
    }
}
