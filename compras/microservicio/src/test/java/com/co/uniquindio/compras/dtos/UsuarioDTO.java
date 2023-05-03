package com.co.uniquindio.compras.dtos;


public class UsuarioDTO {

    private int id;
    private String correo;

    public UsuarioDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @Override
    public String toString() {
        return "UsuarioDTO{" +
                "id=" + id +
                ", correo='" + correo + '\'' +
                '}';
    }
}
