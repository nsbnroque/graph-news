package com.gft.noticias.dto;

import lombok.Data;

@Data
public class UsuarioForm {
    private String nome;
    private String email;
    private String role;
    private String senha;

    public UsuarioForm(String nome, String email, String senha){
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.role = "ROLE_USER";
    }
    
}
