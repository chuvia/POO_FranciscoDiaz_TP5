package edu.programacion.ejercicio2504;

public class TrabajoDescarga implements Trabajo {

    private String archivo;

    public TrabajoDescarga(String archivo) {
        this.archivo = archivo;
    }

    @Override
    public void procesar() {
        System.out.println("Descargando archivo: " + archivo);
    }
}