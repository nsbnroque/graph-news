package com.gft.noticias.dto;

import com.gft.noticias.entity.Usuario;

import lombok.Data;

public class UsuarioMapper {
   /* 
    public static Usuario fromDTO(RegistroUsuarioDTO dto){
        return new Usuario(null,dto.getNome(), null, null);
    }
    */

    public static ConsultaUsuarioDTO fromEntity(Usuario usuario){
        return new ConsultaUsuarioDTO(usuario.getUsuarioId(),usuario.getNome(), usuario.getEmail());
    }
    
}
