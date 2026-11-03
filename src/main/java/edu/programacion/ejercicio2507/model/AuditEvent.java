package edu.programacion.ejercicio2507.model;

import java.time.Instant;
import java.time.format.DateTimeFormatter;

// Capa de Modelo (model).
// Representa un evento que debe quedar registrado en la bitácora.
public class AuditEvent {

    private Instant fecha;
    private String sesionUsuario;
    private String accion;
    private String detalleOperacion;

    public AuditEvent(String sesionUsuario, String accion, String detalleOperacion) {
        this.fecha = Instant.now();
        this.sesionUsuario = sesionUsuario;
        this.accion = accion;
        this.detalleOperacion = detalleOperacion;
    }

    // Formatea el evento siguiendo el formato obligatorio de la consigna.
    public String formatear() {
        return "[" + DateTimeFormatter.ISO_INSTANT.format(fecha) + "] | [" +
                sesionUsuario + "] | [" +
                accion + "] | [" +
                detalleOperacion + "]";
    }
}
