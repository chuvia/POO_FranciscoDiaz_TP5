
package edu.programacion.ejercicio2509.service;

import edu.programacion.ejercicio2509.config.ConexionBD;
import edu.programacion.ejercicio2509.exception.BatchProcessingException;
import edu.programacion.ejercicio2509.model.ArticuloStock;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class StockBatchProcessorImpl implements StockBatchProcessor {

    private static final Path DIRECTORIO_EXPORTACION = Path.of("exports");
    private static final Path ARCHIVO_ERRORES =
            Path.of("var", "log", "fallos_importacion.log");

    // EXPORTACIÓN: base de datos hacia CSV.
    @Override
    public Path exportarInventario() throws BatchProcessingException {

        // Generamos el nombre del archivo con fecha y hora.
        String fecha = LocalDateTime.now().format(
                DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")
        );

        Path archivo = DIRECTORIO_EXPORTACION.resolve(
                "inventario_consolidado_" + fecha + ".csv"
        );

        String sql = """
                SELECT codigo_sku, descripcion, precio_unitario, stock_disponible
                FROM productos
                """;

        try {
            Files.createDirectories(DIRECTORIO_EXPORTACION);

            try (Connection conexion = ConexionBD.conectar();
                 PreparedStatement ps = conexion.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery();
                 BufferedWriter writer = Files.newBufferedWriter(
                         archivo, StandardCharsets.UTF_8)) {

                // Escribimos el encabezado del CSV.
                writer.write("SKU,DESCRIPCION,PRECIO,STOCK");
                writer.newLine();

                // Recorremos los productos de la base de datos.
                while (rs.next()) {

                    writer.write(
                            escaparCSV(rs.getString("codigo_sku")) + "," +
                                    escaparCSV(rs.getString("descripcion")) + "," +
                                    rs.getBigDecimal("precio_unitario").toPlainString() + "," +
                                    rs.getInt("stock_disponible")
                    );

                    writer.newLine();
                }
            }

            System.out.println("Inventario exportado: " + archivo);
            return archivo;

        } catch (IOException | SQLException e) {
            throw new BatchProcessingException(
                    "Error al exportar el inventario", e
            );
        }
    }

    // IMPORTACIÓN: CSV hacia la base de datos.
    @Override
    public void importarInventario(Path archivo)
            throws BatchProcessingException {

        // Guardamos los artículos antes de insertarlos.
        List<ArticuloStock> articulos = new ArrayList<>();

        int numeroLinea = 0;

        // PRIMERA ETAPA: lectura y validación del CSV.
        try (BufferedReader reader = Files.newBufferedReader(
                archivo, StandardCharsets.UTF_8)) {

            String linea = reader.readLine();
            numeroLinea++;

            if (!"SKU,DESCRIPCION,PRECIO,STOCK".equals(linea)) {
                throw new BatchProcessingException(
                        "Encabezado CSV incorrecto", numeroLinea, null
                );
            }

            while ((linea = reader.readLine()) != null) {
                numeroLinea++;

                try {
                    String[] campos = parsearCSV(linea);

                    if (campos.length != 4) {
                        throw new IllegalArgumentException(
                                "Se esperaban 4 campos"
                        );
                    }

                    String sku = campos[0].trim();
                    String descripcion = campos[1].trim();
                    BigDecimal precio = new BigDecimal(campos[2].trim());
                    Integer stock = Integer.valueOf(campos[3].trim());

                    if (sku.isEmpty() || descripcion.isEmpty()
                            || precio.signum() < 0 || stock < 0) {
                        throw new IllegalArgumentException(
                                "Datos inválidos"
                        );
                    }

                    articulos.add(new ArticuloStock(
                            sku, descripcion, precio, stock
                    ));

                } catch (RuntimeException e) {
                    throw new BatchProcessingException(
                            "Registro inválido en línea " + numeroLinea,
                            numeroLinea, e
                    );
                }
            }

        } catch (IOException e) {
            throw new BatchProcessingException(
                    "No se pudo leer el archivo CSV", e
            );
        } catch (BatchProcessingException e) {
            registrarError(e);
            throw e;
        }

        // SEGUNDA ETAPA: inserción transaccional en SQLite.
        String sql = """
                INSERT INTO productos
                (codigo_sku, descripcion, precio_unitario, stock_disponible)
                VALUES (?, ?, ?, ?)
                """;

        try (Connection conexion = ConexionBD.conectar()) {

            // Desactivamos el autocommit para iniciar la transacción.
            conexion.setAutoCommit(false);

            try (PreparedStatement ps = conexion.prepareStatement(sql)) {

                for (ArticuloStock articulo : articulos) {

                    ps.setString(1, articulo.getSku());
                    ps.setString(2, articulo.getDescripcion());
                    ps.setBigDecimal(3, articulo.getPrecio());
                    ps.setInt(4, articulo.getStock());

                    ps.executeUpdate();
                }

                // Si todos se insertaron correctamente, confirmamos.
                conexion.commit();

                System.out.println(
                        "Importación finalizada. Artículos: " + articulos.size()
                );

            } catch (SQLException e) {

                // Si falla alguno, deshacemos todos los cambios.
                conexion.rollback();

                BatchProcessingException error =
                        new BatchProcessingException(
                                "Error al insertar los artículos. "
                                        + "Se realizó rollback.", e
                        );

                registrarError(error);
                throw error;
            }

        } catch (SQLException e) {

            BatchProcessingException error =
                    new BatchProcessingException(
                            "Error en la conexión o transacción", e
                    );

            registrarError(error);
            throw error;
        }
    }

    // Registra los errores de importación en un archivo de log.
    private void registrarError(BatchProcessingException e) {

        try {
            Files.createDirectories(ARCHIVO_ERRORES.getParent());

            try (BufferedWriter writer = Files.newBufferedWriter(
                    ARCHIVO_ERRORES,
                    StandardCharsets.UTF_8,
                    java.nio.file.StandardOpenOption.CREATE,
                    java.nio.file.StandardOpenOption.APPEND)) {

                String fecha = LocalDateTime.now().format(
                        DateTimeFormatter.ISO_LOCAL_DATE_TIME
                );

                writer.write(
                        "[" + fecha + "] | Línea: "
                                + e.getNumeroLinea()
                                + " | Causa: " + e.getMessage()
                );

                writer.newLine();
            }

        } catch (IOException ex) {
            System.out.println(
                    "No se pudo registrar el error: " + ex.getMessage()
            );
        }
    }

    // Escapa comas y comillas para escribir correctamente en CSV.
    private String escaparCSV(String valor) {
        if (valor == null) {
            return "";
        }

        return "\"" + valor.replace("\"", "\"\"") + "\"";
    }

    // Interpreta los campos de una línea CSV, incluyendo comillas.
    private String[] parsearCSV(String linea) {

        List<String> campos = new ArrayList<>();
        StringBuilder campo = new StringBuilder();
        boolean entreComillas = false;

        for (int i = 0; i < linea.length(); i++) {

            char caracter = linea.charAt(i);

            if (caracter == '"') {

                if (entreComillas && i + 1 < linea.length()
                        && linea.charAt(i + 1) == '"') {
                    campo.append('"');
                    i++;
                } else {
                    entreComillas = !entreComillas;
                }

            } else if (caracter == ',' && !entreComillas) {

                campos.add(campo.toString());
                campo.setLength(0);

            } else {
                campo.append(caracter);
            }
        }

        if (entreComillas) {
            throw new IllegalArgumentException("Comillas CSV sin cerrar");
        }

        campos.add(campo.toString());

        return campos.toArray(new String[0]);
    }
}
