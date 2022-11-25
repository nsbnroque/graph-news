package com.gft.noticias.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.gft.noticias.client.Noticias;
import com.gft.noticias.client.NoticiasResponse;

import reactor.core.publisher.Mono;

@Service
public class NoticiasService {
    public List<Noticias> listarNoticias(String q, String date){
        NoticiasResponse retornadas = obterNoticias(q, date);
        List<Noticias> filtradas = filtrarData(retornadas.getList());
        return filtradas;
    }
      
    public NoticiasResponse obterNoticias(String q, String date){
        WebClient webClient =  WebClient.create("https://apinoticias.tedk.com.br/api/");
       Mono<NoticiasResponse> mono = webClient.get()
       .uri(builder -> builder.queryParam("q", q)
                              .queryParam("date", date).build())
       .retrieve()
       .bodyToMono(NoticiasResponse.class);
        return mono.block();       
    }

    public List<Noticias> filtrarData(List<Noticias> noticias){
        List<Noticias> filtradas = new ArrayList<>();
        for(Noticias n : noticias){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); 
            LocalDate hoje =  LocalDate.now();              
            String hojeFormatado = hoje.format(formatter);
            if(n.getDate().equals(hojeFormatado)){
                filtradas.add(n);}              
        }
        return filtradas;
    } 
}
