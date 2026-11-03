package edu.programacion.ejercicio2506;

import java.time.LocalDate;

public class Transaccion {

    private LocalDate fecha;
    private double monto;
    private TipoTransaccion tipo;

    public Transaccion(double monto, TipoTransaccion tipo) {
        this.fecha = LocalDate.now();
        this.monto = monto;
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Fecha: " + fecha +
                " | Monto: $" + monto +
                " | Tipo: " + tipo;
    }
}