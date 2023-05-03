package com.co.uniquindio.compras.dtos;

import com.co.uniquindio.compras.enums.EnumMedioPago;


import java.util.List;

public class CompraDTO {

    private EnumMedioPago medioPago;

    private UsuarioDTO usuario;

    private List<ProductoDTO>productos;

    public CompraDTO() {
    }

    public EnumMedioPago getMedioPago() {
        return medioPago;
    }

    public void setMedioPago(EnumMedioPago medioPago) {
        this.medioPago = medioPago;
    }

    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }

    public List<ProductoDTO> getProductos() {
        return productos;
    }

    public void setProductos(List<ProductoDTO> productos) {
        this.productos = productos;
    }

    @Override
    public String toString() {
        return "CompraDTO{" +
                "medioPago=" + medioPago +
                ", usuario=" + usuario +
                ", productos=" + productos +
                '}';
    }
}

