package com.gft.noticias.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.gft.noticias.entity.Etiqueta;
import com.gft.noticias.exception.EntityNotFoundException;
import com.gft.noticias.repository.EtiquetaRepository;

@SpringBootTest
public class EtiquetaServiceTest {

    @Autowired
    private EtiquetaService service;

    @MockBean
    private EtiquetaRepository repository;


    @Test
    void testSalvarEtiqueta() {
        Etiqueta teste = Etiqueta.builder()
                        .nome("Nova Etiqueta")
                        .build();

        when(repository.save(teste)).thenReturn(teste);
        Etiqueta salva = service.salvarEtiqueta(teste);
        assertEquals(teste.getNome(), salva.getNome());
    }

    @Test
    void quandoSalvarEtiquetaSeNomeJaExiste_NaoDeveSalvarNovamente(){
        int numeroAnterior = repository.findAll().size();
        Etiqueta teste = Etiqueta.builder()
                        .nome("Tecnologia")
                        .build();
        service.salvarEtiqueta(teste);
        int numeroPosterior = repository.findAll().size();

        assertEquals(numeroAnterior, numeroPosterior);
    }

    @Test
    void quandoEncontrarEtiquetaPeloNome_EntaoRetornaEtiqueta(){
       //when(repository.findOneByNome(nome)).thenReturn(null);
        String nome = "Nova Etiqueta";
        Etiqueta encontrada = service.encontrarEtiqueta(nome);
        assertEquals(nome, encontrada.getNome());
    }

    @Test
    void quandoEncontrarEtiquetaPeloNomeSeEtiquetaNaoExiste_EntaoLancaExcecao(){
        String nome = "Nome não existente";
        EntityNotFoundException exception = assertThrows(
            EntityNotFoundException.class, 
            () -> { service.encontrarEtiqueta(nome);}
          );
        
         assertEquals("Etiqueta não encontrada com o nome: Nome não existente", exception.getMessage());
    }

    @Test
    void quandoEncontrarEtiquetaPeloId_EntaoRetornaEtiqueta(){
        Long id = 0L;
        Etiqueta encontrada = service.encontrarEtiquetaPeloId(id);
        assertEquals(id, encontrada.getEtiquetaId());
    }

    @Test
    void quandoEncontrarEtiquetaPeloIdSeEtiquetaNaoExiste_EntaoLancaExcecao(){
        Long id = 5000L;
        EntityNotFoundException exception = assertThrows(
            EntityNotFoundException.class, 
            () -> { service.encontrarEtiquetaPeloId(id);}
          );
        
         assertEquals("Etiqueta não encontrada com o id: 5000", exception.getMessage());
    }

  //TO-DO TESTE DELETE


}
