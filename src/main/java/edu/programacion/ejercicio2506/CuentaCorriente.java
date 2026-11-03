package edu.programacion.ejercicio2506;

public class CuentaCorriente extends CuentaBancaria {

    public CuentaCorriente(Persona titular, double saldo) {
        super(titular, saldo, -15000);
    }

    @Override
    public void extraer(double monto)
            throws SuperaLimiteMinimoException {

        validarExtraccion(monto);

        registrarExtraccion(monto);
    }
}