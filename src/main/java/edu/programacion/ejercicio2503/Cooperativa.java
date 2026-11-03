package edu.programacion.ejercicio2503;

import java.util.ArrayList;

public class Cooperativa {
    private ArrayList<Chofer> socios;

    public Cooperativa() {
        socios = new ArrayList<>();
    }

    public void agregarSocio(Chofer chofer) {
        socios.add(chofer);
    }

    public Chofer realizarViaje(Integer cantidadPasajeros,
                                Double cantidadKms) {
        for (Chofer chofer : socios) {
            if (chofer.realizarViaje(cantidadPasajeros, cantidadKms)) {
                return chofer;
            }
        }

        return null;
    }

    public Colectivo mostrarColectivoConMayorKilometraje() {
        Colectivo mayor = null;

        for (Chofer chofer : socios) {
            Colectivo colectivo = chofer.getColectivo();

            if (mayor == null ||
                    colectivo.getKilometrajeAcumulado()
                            > mayor.getKilometrajeAcumulado()) {
                mayor = colectivo;
            }
        }

        return mayor;
    }
}
