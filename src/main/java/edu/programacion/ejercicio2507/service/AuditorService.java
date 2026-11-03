package edu.programacion.ejercicio2507.service;

import edu.programacion.ejercicio2507.exception.AuditoriaException;
import edu.programacion.ejercicio2507.model.AuditEvent;

// Capa de Servicio (service).
// Define el contrato que deben cumplir los servicios de auditoría.
public interface AuditorService {

    void registrarOperacion(AuditEvent evento) throws AuditoriaException;
}
