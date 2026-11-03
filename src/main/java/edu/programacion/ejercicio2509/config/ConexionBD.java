
package edu.programacion.ejercicio2509.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    // URL de conexión a nuestra base de datos SQLite.
    private static final String URL = "jdbc:sqlite:tienda.db";

    // Devuelve una conexión con la base de datos.
    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL);
    }
}
