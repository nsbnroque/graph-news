package com.gft.noticias.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gft.noticias.client.NoticiasResponse;
import com.gft.noticias.service.NoticiasService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/noticias")
public class NoticiasController {

    private final NoticiasService noticiasService;

    @GetMapping
    public NoticiasResponse buscarNoticias(@RequestParam String q, @RequestParam String date){
        
        
        return noticiasService.obterNoticias(q, date);
    }
    
}
