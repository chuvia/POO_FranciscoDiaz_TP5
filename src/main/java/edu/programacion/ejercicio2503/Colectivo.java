package edu.programacion.ejercicio2503;

public class Colectivo {
    private String patente;
    private String modelo;
    private Integer capacidadPasajeros;
    private Double kilometrajeAcumulado;

    public Colectivo(String patente, String modelo,
                     Integer capacidadPasajeros) {
        this.patente = patente;
        this.modelo = modelo;
        this.capacidadPasajeros = capacidadPasajeros;
        this.kilometrajeAcumulado = 0.0;
    }

    public boolean puedeTransportar(Integer cantidadPasajeros) {
        return cantidadPasajeros <= capacidadPasajeros;
    }

    public void registrarKilometraje(Double cantidadKms) {
        kilometrajeAcumulado += cantidadKms;
    }

    public String getPatente() {
        return patente;
    }

    public String getModelo() {
        return modelo;
    }

    public Integer getCapacidadPasajeros() {
        return capacidadPasajeros;
    }

    public Double getKilometrajeAcumulado() {
        return kilometrajeAcumulado;
    }

    @Override
    public String toString() {
        return "Patente: " + patente
                + ", Modelo: " + modelo
                + ", Kilometraje: " + kilometrajeAcumulado + " km";
    }
}
