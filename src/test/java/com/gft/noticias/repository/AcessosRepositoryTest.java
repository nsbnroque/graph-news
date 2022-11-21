package com.gft.noticias.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.gft.noticias.dto.ContagemAcessosDTO;

@SpringBootTest
public class AcessosRepositoryTest {
    @Autowired
    private AcessosRepository repository;

    @Test
    void testCountAcessos() {
       ContagemAcessosDTO etiquetasHistorico = repository.countAcessos("tecnologia");      
       System.out.println(etiquetasHistorico);
    }

    @Test
    void testCountAcessosEtiquetas() {

    }
}
