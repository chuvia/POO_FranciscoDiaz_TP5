package edu.programacion.ejercicio2508.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    private static final String URL = "jdbc:sqlite:tienda.db";

    public static Connection obtenerConexion() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    public static void crearTabla() throws SQLException {

        String sql = """
            CREATE TABLE IF NOT EXISTS productos (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                codigo_sku VARCHAR(50) NOT NULL UNIQUE,
                descripcion VARCHAR(255) NOT NULL,
                precio_unitario DECIMAL(10,2) NOT NULL,
                stock_disponible INTEGER NOT NULL
            )
            """;

        try (Connection conexion = obtenerConexion();
             java.sql.Statement statement = conexion.createStatement()) {

            statement.execute(sql);
        }
    }
}