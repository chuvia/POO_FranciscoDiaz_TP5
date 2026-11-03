
package edu.programacion.ejercicio2509.model;

import java.math.BigDecimal;

public class ArticuloStock {

    private String sku;
    private String descripcion;
    private BigDecimal precio;
    private Integer stock;

    public ArticuloStock(String sku, String descripcion,
                         BigDecimal precio, Integer stock) {
        this.sku = sku;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
    }

    public String getSku() {
        return sku;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public Integer getStock() {
        return stock;
    }

    @Override
    public String toString() {
        return "ArticuloStock{" +
                "sku='" + sku + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", precio=" + precio +
                ", stock=" + stock +
                '}';
    }
}
