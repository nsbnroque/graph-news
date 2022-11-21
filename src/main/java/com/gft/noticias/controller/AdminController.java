package com.gft.noticias.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gft.noticias.entity.Etiqueta;
import com.gft.noticias.entity.Usuario;
import com.gft.noticias.service.UsuarioService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final UsuarioService usuarioService;

    @GetMapping("/{id}")
    public Usuario encontrarUsuario(@PathVariable Long id){     
        return usuarioService.encontrarUsuario(id);
    }

    @GetMapping("/usuarios")
    public Page<Usuario> listarUsuarios(Pageable pageable){
        return usuarioService.listarUsuarios(pageable);
    }

    @GetMapping("/etiquetas")
    public Page<Etiqueta> listarEtiquetas(Pageable pageable){
        return null;
    }

}
