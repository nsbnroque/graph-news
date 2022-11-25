package com.gft.noticias.dto;

import com.gft.noticias.entity.Usuario;

public class UsuarioMapper {

    public static ConsultaUsuarioDTO fromEntity(Usuario usuario){
        return new ConsultaUsuarioDTO(usuario.getUsuarioId(),usuario.getNome(), usuario.getEmail());
    }

    public static Usuario toEntity(Long id,UsuarioForm form) {
        return new Usuario(id,form.getNome(),form.getEmail(),null,null,null,null);
    }

    public static Usuario toEntity(Long id,RegistroForm form) {
        return new Usuario(id,form.getNome(),form.getEmail(),form.getSenha(),null,null,null);
    }
    
}
