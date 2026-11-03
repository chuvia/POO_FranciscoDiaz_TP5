package edu.programacion.ejercicio2508.model;

import java.math.BigDecimal;

public class Producto {

    private Long id;
    private String codigoSku;
    private String descripcion;
    private BigDecimal precioUnitario;
    private Integer stockDisponible;

    public Producto(Long id, String codigoSku, String descripcion,
                    BigDecimal precioUnitario, Integer stockDisponible) {

        this.id = id;
        this.codigoSku = codigoSku;
        this.descripcion = descripcion;
        this.precioUnitario = precioUnitario;
        this.stockDisponible = stockDisponible;
    }

    public Producto(String codigoSku, String descripcion,
                    BigDecimal precioUnitario, Integer stockDisponible) {

        this(null, codigoSku, descripcion, precioUnitario, stockDisponible);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigoSku() {
        return codigoSku;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public Integer getStockDisponible() {
        return stockDisponible;
    }

    public void setStockDisponible(Integer stockDisponible) {
        this.stockDisponible = stockDisponible;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "id=" + id +
                ", codigoSku='" + codigoSku + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", precioUnitario=" + precioUnitario +
                ", stockDisponible=" + stockDisponible +
                '}';
    }
}