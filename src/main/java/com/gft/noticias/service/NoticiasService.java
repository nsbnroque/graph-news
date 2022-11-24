package com.gft.noticias.service;

import java.time.LocalDateTime;
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

    /* 
    public List<Noticias> filtrarData(List<Noticias> noticias){
        List<Noticias> filtradas = new ArrayList<>();
        for(Noticias n : noticias){
            n.getDatetime().replace(" às ", " ");
            System.out.println(n.getDatetime());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");       
            LocalDateTime dateTime = LocalDateTime.parse(n.getDatetime(), formatter);
            LocalDateTime agora = LocalDateTime.now();
            

        }
        return filtradas;
    }

    static final long DAY = 24 * 60 * 60 * 1000;
    public boolean inLastDay(Date agora) {
    return agora.getTime() > System.currentTimeMillis() - DAY;
    }
*/

    
    
}
