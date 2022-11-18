package com.gft.noticias.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.gft.noticias.client.NoticiasResponse;

import reactor.core.publisher.Mono;

@Service
public class NoticiasService {
      
    public NoticiasResponse obterNoticias(String q, String date){
        WebClient webClient =  WebClient.create("https://apinoticias.tedk.com.br/api/");
       Mono<NoticiasResponse> mono = webClient.get()
       .uri(builder -> builder.queryParam("q", q)
                              .queryParam("date", date).build())
       .retrieve()
       .bodyToMono(NoticiasResponse.class);
       
       //mono.subscribe(noticiasResponse -> {System.out.println(noticiasResponse);
    //});
        return mono.block();       
    }
    
    
}
