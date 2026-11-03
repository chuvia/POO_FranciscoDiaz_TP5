package edu.programacion.ejercicio2506;

import java.time.LocalDate;
import java.util.ArrayList;

public abstract class CuentaBancaria {

    protected double saldo;
    protected double limiteMinimo;
    protected Persona titular;
    protected LocalDate fechaApertura;
    protected ArrayList<Transaccion> transacciones;

    public CuentaBancaria(Persona titular, double saldo, double limiteMinimo) {
        this.titular = titular;
        this.saldo = saldo;
        this.limiteMinimo = limiteMinimo;
        this.fechaApertura = LocalDate.now();
        this.transacciones = new ArrayList<>();
    }

    // Registra un depósito y guarda la transacción realizada.
    public void depositar(double monto) {
        saldo += monto;
        transacciones.add(new Transaccion(monto, TipoTransaccion.DEPOSITO));
    }

    // Consulta el saldo actual de la cuenta.
    public double consultarSaldo() {
        return saldo;
    }

    // Verifica que la extracción no deje el saldo debajo del límite mínimo.
    protected void validarExtraccion(double monto)
            throws SuperaLimiteMinimoException {

        if (saldo - monto < limiteMinimo) {
            throw new SuperaLimiteMinimoException();
        }
    }

    // Registra una extracción exitosa.
    protected void registrarExtraccion(double monto) {
        saldo -= monto;
        transacciones.add(new Transaccion(monto, TipoTransaccion.EXTRACCION));
    }

    // Cada tipo de cuenta implementa su propia forma de extraer.
    public abstract void extraer(double monto)
            throws SuperaLimiteMinimoException,
            SuperaCantidadExtraccionesException;

    // Muestra todas las transacciones realizadas.
    public void mostrarTransacciones() {
        for (Transaccion transaccion : transacciones) {
            System.out.println(transaccion);
        }
    }
}