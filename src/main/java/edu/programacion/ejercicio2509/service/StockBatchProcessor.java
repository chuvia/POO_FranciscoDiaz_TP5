
package edu.programacion.ejercicio2509.service;

import edu.programacion.ejercicio2509.exception.BatchProcessingException;

import java.io.IOException;
import java.nio.file.Path;

public interface StockBatchProcessor {

    // Exporta todos los artículos de la base de datos a un archivo CSV.
    Path exportarInventario() throws BatchProcessingException;

    // Importa los artículos de un archivo CSV a la base de datos.
    void importarInventario(Path archivo) throws BatchProcessingException;
}
