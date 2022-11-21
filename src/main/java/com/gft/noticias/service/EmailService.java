package com.gft.noticias.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.gft.noticias.client.Noticias;
import com.gft.noticias.client.NoticiasResponse;
import com.gft.noticias.entity.Etiqueta;
import com.gft.noticias.entity.Usuario;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final UsuarioService usuarioService;
    private final NoticiasService noticiasService;

    public List<Noticias> gerarEmail(Usuario usuario){
        return null;
    }

    public List<NoticiasResponse> gerarInteresses(Usuario usuario, LocalDate data){
        List<NoticiasResponse> noticias = new ArrayList<>();
        List<Etiqueta> etiquetas = usuarioService.listarEtiquetas(usuario);
        String date = data.toString();   
        for(Etiqueta e : etiquetas){
            noticias.add(noticiasService.obterNoticias(e.getNome(), date));
        }
        return noticias;
    }

    public List<NoticiasResponse> gerarRecomendacao(Usuario usuario, LocalDate data){
        List<NoticiasResponse> noticias = new ArrayList<>();
        List<Etiqueta> etiquetas = usuarioService.listarRecomendacoes(usuario);
        String date = data.toString();   
        for(Etiqueta e : etiquetas){
            noticias.add(noticiasService.obterNoticias(e.getNome(), date));
        }
        return noticias;
    }

    public List<NoticiasResponse> gerarTrends(Usuario usuario, LocalDate data){
        List<NoticiasResponse> noticias = new ArrayList<>();
        List<Etiqueta> etiquetas = usuarioService.listarTrends(usuario);
        String date = data.toString();   
        for(Etiqueta e : etiquetas){
            noticias.add(noticiasService.obterNoticias(e.getNome(), date));
        }
        return noticias;
    }
    
}
