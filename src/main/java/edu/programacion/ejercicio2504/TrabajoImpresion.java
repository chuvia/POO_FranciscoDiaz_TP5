package edu.programacion.ejercicio2504;

public class TrabajoImpresion implements Trabajo {

    private String documento;

    public TrabajoImpresion(String documento) {
        this.documento = documento;
    }

    @Override
    public void procesar() {
        System.out.println("Imprimiendo documento: " + documento);
    }
}