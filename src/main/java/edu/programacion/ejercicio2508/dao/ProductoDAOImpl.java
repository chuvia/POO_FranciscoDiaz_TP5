package edu.programacion.ejercicio2508.dao;

import edu.programacion.ejercicio2508.config.ConexionBD;
import edu.programacion.ejercicio2508.exception.DAOException;
import edu.programacion.ejercicio2508.model.Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductoDAOImpl implements ProductoDAO {

    // Guarda un producto y recupera el ID generado automáticamente.
    @Override
    public void guardar(Producto producto) throws DAOException {

        String sql = "INSERT INTO productos " +
                "(codigo_sku, descripcion, precio_unitario, stock_disponible) " +
                "VALUES (?, ?, ?, ?)";

        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement statement = conexion.prepareStatement(
                     sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, producto.getCodigoSku());
            statement.setString(2, producto.getDescripcion());
            statement.setBigDecimal(3, producto.getPrecioUnitario());
            statement.setInt(4, producto.getStockDisponible());

            statement.executeUpdate();

            // Recuperamos la clave primaria generada por la base de datos.
            try (ResultSet resultado = statement.getGeneratedKeys()) {

                if (resultado.next()) {
                    producto.setId(resultado.getLong(1));
                }
            }

        } catch (SQLException e) {

            throw new DAOException("Error al guardar el producto.", e);
        }
    }

    // Busca un producto utilizando su ID.
    @Override
    public Optional<Producto> buscarPorId(Long id) throws DAOException {

        String sql = "SELECT * FROM productos WHERE id = ?";

        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement statement = conexion.prepareStatement(sql)) {

            statement.setLong(1, id);

            try (ResultSet resultado = statement.executeQuery()) {

                if (resultado.next()) {
                    return Optional.of(mapearProducto(resultado));
                }

                return Optional.empty();
            }

        } catch (SQLException e) {

            throw new DAOException("Error al buscar el producto por ID.", e);
        }
    }

    // Busca un producto utilizando su código SKU.
    @Override
    public Optional<Producto> buscarPorSku(String sku) throws DAOException {

        String sql = "SELECT * FROM productos WHERE codigo_sku = ?";

        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement statement = conexion.prepareStatement(sql)) {

            statement.setString(1, sku);

            try (ResultSet resultado = statement.executeQuery()) {

                if (resultado.next()) {
                    return Optional.of(mapearProducto(resultado));
                }

                return Optional.empty();
            }

        } catch (SQLException e) {

            throw new DAOException("Error al buscar el producto por SKU.", e);
        }
    }

    // Recupera todos los productos de la tabla.
    @Override
    public List<Producto> listarTodos() throws DAOException {

        String sql = "SELECT * FROM productos";
        List<Producto> productos = new ArrayList<>();

        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement statement = conexion.prepareStatement(sql);
             ResultSet resultado = statement.executeQuery()) {

            while (resultado.next()) {

                productos.add(mapearProducto(resultado));
            }

            return productos;

        } catch (SQLException e) {

            throw new DAOException("Error al listar los productos.", e);
        }
    }

    // Actualiza el stock de un producto.
    @Override
    public void actualizarStock(Long id, Integer nuevoStock)
            throws DAOException {

        String sql = "UPDATE productos SET stock_disponible = ? WHERE id = ?";

        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement statement = conexion.prepareStatement(sql)) {

            statement.setInt(1, nuevoStock);
            statement.setLong(2, id);

            statement.executeUpdate();

        } catch (SQLException e) {

            throw new DAOException("Error al actualizar el stock.", e);
        }
    }

    // Elimina un producto utilizando su ID.
    @Override
    public void eliminar(Long id) throws DAOException {

        String sql = "DELETE FROM productos WHERE id = ?";

        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement statement = conexion.prepareStatement(sql)) {

            statement.setLong(1, id);

            statement.executeUpdate();

        } catch (SQLException e) {

            throw new DAOException("Error al eliminar el producto.", e);
        }
    }

    // Convierte una fila del ResultSet en un objeto Producto.
    // El objeto se construye antes de cerrar el ResultSet.
    private Producto mapearProducto(ResultSet resultado) throws SQLException {

        return new Producto(
                resultado.getLong("id"),
                resultado.getString("codigo_sku"),
                resultado.getString("descripcion"),
                resultado.getBigDecimal("precio_unitario"),
                resultado.getInt("stock_disponible")
        );
    }
}