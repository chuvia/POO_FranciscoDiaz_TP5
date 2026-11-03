package edu.programacion;


import edu.programacion.ejercicio2501.Cancion;
import edu.programacion.ejercicio2501.Pelicula;
import edu.programacion.ejercicio2501.Reproducible;
import edu.programacion.ejercicio2501.ReproductorMultimedia;
import edu.programacion.ejercicio2501.Video;

public class Main {
    public static void main(String[] args) {




                ReproductorMultimedia reproductor =
                        new ReproductorMultimedia();

                Video video = new Video("Video de prueba");
                Cancion cancion = new Cancion("Mi canción");
                Pelicula pelicula = new Pelicula("Mi película");

                reproductor.cargar(video);
                reproductor.play();
                reproductor.mostrarContenido();

                System.out.println();

                reproductor.cargar(cancion);
                reproductor.bucle();
                reproductor.mostrarContenido();

                System.out.println();

                reproductor.stop();
                reproductor.mostrarContenido();

                System.out.println();

                reproductor.cargar(pelicula);
                reproductor.play();
                reproductor.mostrarContenido();
            }






}