package com.gft.noticias.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.gft.noticias.entity.Etiqueta;
import com.gft.noticias.exception.EntityNotFoundException;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EtiquetaServiceUnitTest {

    @Autowired
    EtiquetaService service;

    private static Etiqueta etiqueta;

    @BeforeAll
    static void setup(){
        etiqueta = Etiqueta.builder()
                            .nome("etiqueta")
                            .build();                      
    }

    @Test
    @Order(1)
    void quandoEncontrarEtiquetaPeloId_EntaoRetornaEtiqueta(){
        Etiqueta salva = this.service.salvarEtiqueta(etiqueta);
        Etiqueta retornada = this.service.encontrarEtiquetaPeloId(salva.getEtiquetaId());
        assertEquals(retornada.getNome(), etiqueta.getNome());
    }

    @Test
    @Order(2)
    void quandoEncontrarEtiquetaPeloNome_EntaoRetornaEtiqueta(){
        Etiqueta retornada = this.service.encontrarEtiqueta("etiqueta").get();
        assertEquals(etiqueta.getNome(), retornada.getNome());
    }

    @Test 
    @Order(3)
    void quandoSalvarEtiqueta_EntaoTransformaEmLetrasMinusculas(){
        Etiqueta maiscula = Etiqueta.builder()
                                    .nome("TUDOGRANDE")
                                    .build();
        Etiqueta salva = this.service.salvarEtiqueta(maiscula);
        assertEquals("tudogrande", salva.getNome());
    }

    @Test
    @Order(4)
    void quandoSalvarEtiquetaComNomeExistente_RetornaEtiquetaJaSalva(){
        Etiqueta retornada = this.service.encontrarEtiqueta("etiqueta").get();
        Etiqueta salva = this.service.salvarEtiqueta(etiqueta);
        assertTrue(retornada.getEtiquetaId() == salva.getEtiquetaId());
    }

    @Test
    @Order(5)
    void quandoEncontrarEtiquetaPeloNomeNaoExistente_EntaoLancaExcecao(){
        String nome = "etiqueta que não existe";
        assertThrows(EntityNotFoundException.class, ()-> {service.encontrarEtiqueta(nome);});
    }

    @Test
    @Order(6)
    void quandoEncontrarEtiquetaPeloNomeNaoExistente_EntaoLancaMensagem(){
        String nome = "etiqueta que não existe";
        String mensagem = "Etiqueta não encontrada com o nome: " +nome;
        EntityNotFoundException thrown = assertThrows(EntityNotFoundException.class, 
                                () -> { service.encontrarEtiqueta(nome);});
                     
        assertEquals(mensagem, thrown.getMessage());
    }

    @Test
    @Order(7)
    void quandoExcluirEtiquetaPeloNome_EntaoxcluiEtiqueta(){
        String nome="tudogrande";
        service.excluirEtiquetaPeloNome(nome);
        assertThrows(EntityNotFoundException.class, ()-> {service.encontrarEtiqueta(nome);});

    }

    @Test
    @Order(8)
    void quandoExcluirEtiquetaPeloid_EntaoxcluiEtiqueta(){
        Long encontrado = this.service.encontrarEtiqueta("etiqueta").get().getEtiquetaId();
        service.excluirEtiqueta(encontrado);
        assertThrows(EntityNotFoundException.class, ()-> {service.encontrarEtiquetaPeloId(encontrado);});
        
    }




    
}
