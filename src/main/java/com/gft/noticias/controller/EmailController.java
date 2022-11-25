package com.gft.noticias.controller;

import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
        
        /*
        LocalDate localDate = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String dataString = localDate.format(formatter);
             Usuario encontrado = usuarioService.encontrarUsuario(id);
        try {
           List<Noticias> noticias = emailService.gerarInteresses(encontrado,dataString);
           ObjectMapper mapper = new ObjectMapper();
           String newJsonData = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(noticias);
           emailService.enviarEmail(encontrado.getEmail(), newJsonData, "Notícias do dia");
           return ResponseEntity.ok(noticias);
           
        }catch(Exception e){
            return ResponseEntity.noContent().build();
        }    
         */    
        return ResponseEntity.ok(emailService.gerarEmail(id));
    }

    @RequestMapping("/enviar")
    public ResponseEntity<List<Noticias>> enviarEmailParaTodos() throws UnsupportedEncodingException{
            List<Usuario> usuarios = usuarioService.listar();
            LocalDate localDate = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String dataString = localDate.format(formatter);
        for(Usuario u : usuarios){
            List<Noticias> noticias = emailService.gerarInteresses(u,dataString);
            if (!noticias.isEmpty()){
                ObjectMapper mapper = new ObjectMapper();
                String newJsonData;
                try {
                    newJsonData = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(noticias);
                    emailService.enviarEmail(u.getEmail(), newJsonData, "Notícias do dia");
                    return ResponseEntity.ok(noticias);
                } catch (JsonProcessingException e) {
                    e.getMessage();                   
                }
            }
    }
    return ResponseEntity.badRequest().build();
}
}
