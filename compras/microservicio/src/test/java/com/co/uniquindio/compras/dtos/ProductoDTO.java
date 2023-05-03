package com.co.uniquindio.compras.dtos;

import java.math.BigDecimal;

public class ProductoDTO {

    private Integer id;
    private String referencia;
    private String nombre;
    private BigDecimal precio;
    private int cantidadCompra;

    public ProductoDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public int getCantidadCompra() {
        return cantidadCompra;
    }

    public void setCantidadCompra(int cantidadCompra) {
        this.cantidadCompra = cantidadCompra;
    }

    @Override
    public String toString() {
        return "ProductoDTO{" +
                "id=" + id +
                ", referencia='" + referencia + '\'' +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", cantidadCompra=" + cantidadCompra +
                '}';
    }
}
