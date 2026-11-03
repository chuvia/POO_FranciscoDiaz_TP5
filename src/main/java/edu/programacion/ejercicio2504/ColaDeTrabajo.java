package edu.programacion.ejercicio2504;

import java.util.LinkedList;
import java.util.Queue;

// a) Implementación de la clase ColaDeTrabajo.
// Permite encolar distintos tipos de trabajos que implementen Trabajo

public class ColaDeTrabajo {

    private String nombre;
    private boolean lista;
    private Queue<Trabajo> trabajos;

    public ColaDeTrabajo(String nombre, boolean lista) {
        this.nombre = nombre;
        this.lista = lista;
        this.trabajos = new LinkedList<>();
    }

    public void encolar(Trabajo trabajo) {
        trabajos.add(trabajo);
    }

    // a) Método sacar(): devuelve el próximo trabajo de la cola.
    // b) Las excepciones se lanzan utilizando throw.
    // throws declara que este método puede lanzar ambas excepciones.
    // c) Las excepciones se capturan utilizando try-catch.
    // Cada catch permite manejar una excepción diferente.

    public Trabajo sacar() throws NoListaException, SinTrabajoEnColaException {

        if (!lista) {
            throw new NoListaException(nombre, (long) trabajos.size());
        }

        if (trabajos.isEmpty()) {
            throw new SinTrabajoEnColaException(nombre);
        }

        return trabajos.poll();
    }

    public void setLista(boolean lista) {
        this.lista = lista;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean isLista() {
        return lista;
    }

    public int cantidadTrabajos() {
        return trabajos.size();
    }
}
