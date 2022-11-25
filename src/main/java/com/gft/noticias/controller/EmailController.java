package com.gft.noticias.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
    public ResponseEntity<List<Noticias>> enviarEmail(@PathVariable Long id){
        LocalDate localDate = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String dataString = localDate.format(formatter);
        Usuario encontrado = usuarioService.encontrarUsuario(id);
        return ResponseEntity.ok(emailService.gerarInteresses(encontrado,dataString));
    }

    public ResponseEntity<String> enviarEmailParaTodos(){
        return ResponseEntity.ok("Email enviado para todos os usuários.");
    }
    
}
