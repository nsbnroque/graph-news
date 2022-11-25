package com.gft.noticias.dto;

import com.gft.noticias.entity.Admin;

public class AdminMapper {

    public static Admin toEntity(Long id,UsuarioForm form) {
        return new Admin(id,form.getNome(),form.getEmail(),null,null);
    }

    public static Admin toEntity(Long id,RegistroForm form) {
        return new Admin(id,form.getNome(),form.getEmail(),form.getSenha(),null);
    }
}
