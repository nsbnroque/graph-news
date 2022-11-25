package com.gft.noticias.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gft.noticias.client.Noticias;
import com.gft.noticias.entity.Etiqueta;
import com.gft.noticias.entity.Usuario;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final UsuarioService usuarioService;
    private final NoticiasService noticiasService;

    private final JavaMailSender mailSender;

    public String enviarEmail(String toEmail, String body, String subject){

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("gft-noticias@outlook.com");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);
        mailSender.send(message);
        return "Email enviado com sucesso";
    }

    public List<Noticias> gerarEmail(Long id){
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataString = localDate.format(formatter);
         Usuario encontrado = usuarioService.encontrarUsuario(id);
        List<Noticias> noticias = gerarInteresses(encontrado,dataString);
        ObjectMapper mapper = new ObjectMapper();
        String newJsonData;
        try {
            newJsonData = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(noticias);
            enviarEmail(encontrado.getEmail(), newJsonData, "Notícias do dia");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return noticias;
    }

    public List<Noticias> gerarInteresses(Usuario usuario, String data){
        List<Noticias> noticias = new ArrayList<>();
        List<Etiqueta> etiquetas = usuarioService.listarEtiquetas(usuario);   
        for(Etiqueta e : etiquetas){
            List<Noticias> novo = noticiasService.listarNoticias(e.getNome(), data);
            if (novo != null) noticias.addAll(novo);
        }
        return noticias;
    }
    
}
