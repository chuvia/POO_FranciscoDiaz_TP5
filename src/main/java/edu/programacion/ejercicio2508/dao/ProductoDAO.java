package edu.programacion.ejercicio2508.dao;

import edu.programacion.ejercicio2508.model.Producto;
import edu.programacion.ejercicio2508.exception.DAOException;

import java.util.List;
import java.util.Optional;

public interface ProductoDAO {

    // Guarda un producto y le asigna su ID generado por la base de datos.
    void guardar(Producto producto) throws DAOException;

    // Busca un producto por su clave primaria.
    Optional<Producto> buscarPorId(Long id) throws DAOException;

    // Busca un producto por su código SKU.
    Optional<Producto> buscarPorSku(String sku) throws DAOException;

    // Devuelve todos los productos.
    List<Producto> listarTodos() throws DAOException;

    // Actualiza el stock de un producto.
    void actualizarStock(Long id, Integer nuevoStock) throws DAOException;

    // Elimina un producto por su ID.
    void eliminar(Long id) throws DAOException;
}