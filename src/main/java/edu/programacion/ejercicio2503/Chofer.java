package edu.programacion.ejercicio2503;

public class Chofer {
    private String nombre;
    private Colectivo colectivo;

    public Chofer(String nombre, Colectivo colectivo) {
        this.nombre = nombre;
        this.colectivo = colectivo;
    }

    public boolean realizarViaje(Integer cantidadPasajeros,
                                 Double cantidadKms) {
        if (colectivo.puedeTransportar(cantidadPasajeros)) {
            colectivo.registrarKilometraje(cantidadKms);
            return true;
        }

        return false;
    }

    public String getNombre() {
        return nombre;
    }

    public Colectivo getColectivo() {
        return colectivo;
    }
}
