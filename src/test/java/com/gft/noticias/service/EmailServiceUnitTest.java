package com.gft.noticias.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceUnitTest {

    @Autowired
    private EmailService emailService;
    @Test
    void testEnviarEmail() {
        String mensagem = emailService.enviarEmail("emailquenaoexiste@provedorfalso.com", 
                                            "Testando sua aplicação",
                                        "Spring Boot Email Test");

        assertEquals("Email enviado com sucesso", mensagem);
    }

    @Test
    void testGerarEmail() {

    }

    @Test
    void testGerarInteresses() {

    }

    @Test
    void testGerarRecomendacao() {

    }

    @Test
    void testGerarTrends() {

    }
}
