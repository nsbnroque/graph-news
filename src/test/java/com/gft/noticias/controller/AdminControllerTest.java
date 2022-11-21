package com.gft.noticias.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.gft.noticias.service.AdminService;

@SpringBootTest
public class AdminControllerTest {

    @Autowired
    AdminService service;

    @BeforeAll
    static void setup(){
        
    }
    
    @Test
    void quandoCadastrarAdministrador_EntaoRetornaAdministrador() {

    }

    @Test
    void cadastrarUsuario() {

    }

    @Test
    void historicoParametros() {

    }

    @Test
    void etiquetasMaisAcessadas(){

    }

    @Test
    void enviarEmail(){

    }

    
}
