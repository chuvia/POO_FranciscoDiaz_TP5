package edu.programacion.ejercicio2505;

public class Main {

    public static void main(String[] args) {

        DataBag bolsa = new DataBag(3);

        // Agregamos distintos tipos de objetos.
        try {

            bolsa.add("Hola");
            bolsa.add(25);
            bolsa.add(3.14);

            System.out.println("Elementos agregados correctamente.");

            // Intentamos agregar un elemento cuando está llena.
            bolsa.add("Otro elemento");

        } catch (FullDataBagException e) {

            System.out.println(e.getMessage());
        }

        System.out.println("\nElementos de la bolsa:");
        bolsa.mostrarElementos();

        // Eliminamos elementos.
        try {

            bolsa.remove("Hola");
            bolsa.remove(25);
            bolsa.remove(3.14);

            // Intentamos eliminar cuando está vacía.
            bolsa.remove("Otro elemento");

        } catch (EmptyDataBagException e) {

            System.out.println(e.getMessage());
        }
    }
}