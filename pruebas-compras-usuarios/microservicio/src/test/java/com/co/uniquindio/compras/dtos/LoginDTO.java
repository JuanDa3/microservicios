package com.co.uniquindio.compras.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


public class LoginDTO {
    private final String email;
    private final String password;

    public LoginDTO(String email, String contrasenia){
        this.email = email;
        this.password = contrasenia;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
