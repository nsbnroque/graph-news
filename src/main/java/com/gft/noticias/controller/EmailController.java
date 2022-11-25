package com.gft.noticias.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gft.noticias.client.Noticias;
import com.gft.noticias.entity.Usuario;
import com.gft.noticias.service.EmailService;
import com.gft.noticias.service.UsuarioService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/email")
public class EmailController {

    private final EmailService emailService;
    private final UsuarioService usuarioService;
    
    @RequestMapping("/{id}")
    public ResponseEntity<List<Noticias>> enviarEmail(@PathVariable Long id) throws UnsupportedEncodingException{    
        return ResponseEntity.ok(emailService.gerarEmail(id));
    }

    @RequestMapping("/enviar")
    public ResponseEntity<String> enviarEmailParaTodos() throws UnsupportedEncodingException{
        List<Usuario> usuarios = usuarioService.listar();
        for (Usuario u : usuarios){
            emailService.gerarEmail(u.getUsuarioId());
        }
    return ResponseEntity.ok("Emails enviados para todos os usuarios.");
}
}
