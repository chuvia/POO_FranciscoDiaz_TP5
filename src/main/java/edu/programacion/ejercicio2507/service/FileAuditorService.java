package edu.programacion.ejercicio2507.service;

import edu.programacion.ejercicio2507.exception.AuditoriaException;
import edu.programacion.ejercicio2507.model.AuditEvent;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

// Implementación concreta del servicio de auditoría.
// Utiliza Java NIO.2 para guardar los eventos en un archivo local.
public class FileAuditorService implements AuditorService {

    private final Path directorio = Path.of("var", "log");
    private final Path archivo = directorio.resolve("auditoria_operaciones.log");

    @Override
    public void registrarOperacion(AuditEvent evento) throws AuditoriaException {

        try {

            // Crea el directorio si todavía no existe.
            Files.createDirectories(directorio);

            // Abre el archivo para agregar información al final.
            // CREATE: crea el archivo si no existe.
            // APPEND: agrega el contenido sin borrar lo anterior.
            try (BufferedWriter writer = Files.newBufferedWriter(
                    archivo,
                    StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND)) {

                writer.write(evento.formatear());
                writer.newLine();
            }

        } catch (IOException e) {

            // Convertimos el error de entrada/salida en nuestra excepción.
            throw new AuditoriaException(
                    "No se pudo registrar la operación de auditoría.", e);
        }
    }
}