package edu.programacion.ejercicio2505;

import java.util.ArrayList;

public class DataBag {

    private int capacidadMaxima;
    private ArrayList<Object> elementos;

    public DataBag(int capacidadMaxima) {
        this.capacidadMaxima = capacidadMaxima;
        this.elementos = new ArrayList<>();
    }

    // Permite agregar elementos a la bolsa.
    // Si la bolsa está llena, lanza FullDataBagException.
    public void add(Object elemento) throws FullDataBagException {

        if (elementos.size() >= capacidadMaxima) {
            throw new FullDataBagException();
        }

        elementos.add(elemento);
    }

    // Permite remover un elemento de la bolsa.
    // Si la bolsa está vacía, lanza EmptyDataBagException.
    public void remove(Object elemento) throws EmptyDataBagException {

        if (elementos.isEmpty()) {
            throw new EmptyDataBagException();
        }

        elementos.remove(elemento);
    }

    public int cantidadElementos() {
        return elementos.size();
    }

    public void mostrarElementos() {
        for (Object elemento : elementos) {
            System.out.println(elemento);
        }
    }
}