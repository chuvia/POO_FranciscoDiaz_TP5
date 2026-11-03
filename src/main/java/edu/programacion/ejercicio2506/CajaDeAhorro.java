package edu.programacion.ejercicio2506;

public class CajaDeAhorro extends CuentaBancaria {

    private int cantidadExtracciones;
    private final int MAXIMO_EXTRACCIONES = 5;

    public CajaDeAhorro(Persona titular, double saldo) {
        super(titular, saldo, 0);
        this.cantidadExtracciones = 0;
    }

    @Override
    public void extraer(double monto)
            throws SuperaLimiteMinimoException,
            SuperaCantidadExtraccionesException {

        if (cantidadExtracciones >= MAXIMO_EXTRACCIONES) {
            throw new SuperaCantidadExtraccionesException();
        }

        validarExtraccion(monto);

        registrarExtraccion(monto);
        cantidadExtracciones++;
    }

    // Reinicia manualmente el contador de extracciones.
    public void resetearExtracciones() {
        cantidadExtracciones = 0;
    }
}