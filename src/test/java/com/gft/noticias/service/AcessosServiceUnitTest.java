package com.gft.noticias.service;

import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.gft.noticias.dto.ContagemAcessosDTO;
import com.gft.noticias.entity.Etiqueta;

@SpringBootTest
public class AcessosServiceUnitTest {

    @Autowired
    AcessosService acessosService;
    @Test
    void quandoChamarContarAcessos_EntaoRetornaNomeEQuantidadeDeAcessos(){
        Etiqueta etiqueta = Etiqueta.builder().nome("tecnologia").build();
        Collection<ContagemAcessosDTO> acessos = acessosService.contarAcessos(etiqueta);
        System.out.println(acessos);
    }
    
    @Test
    void testEtiquetasMaioresAcessos() {

    }

    @Test
    void testMaioresAcessos() {

    }
}
