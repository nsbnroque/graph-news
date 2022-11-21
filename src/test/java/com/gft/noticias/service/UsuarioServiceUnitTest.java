package com.gft.noticias.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.gft.noticias.entity.Etiqueta;
import com.gft.noticias.entity.Usuario;
import com.gft.noticias.exception.DuplicatedUniquePropertyException;
import com.gft.noticias.projections.UsuarioEtiquetasProjection;
import com.gft.noticias.repository.AcessosRepository;

@SpringBootTest
public class UsuarioServiceUnitTest {

    @Autowired
    private UsuarioService service;
    @Autowired
    private AcessosRepository acessosRepository;

    private static Usuario usuario;



    @BeforeAll
    static void setup(){
        usuario = Usuario.builder()
                         .nome("Gael dos Santos")
                         .email("gasa@gft.com")
                         .build();                      
    }


    @Test
    void quandoEncontrarUsuarioPeloId_EntaoRetornaUsuario(){
        Usuario salvo = this.service.salvarUsuario(usuario);
        Usuario retornado = this.service.encontrarUsuario(salvo.getUsuarioId());
        assertEquals(retornado.getEmail(), usuario.getEmail());
    }

    @Test
    void quandoEncontrarUsuarioPeloEmail_EntaoRetornaUsuario(){
        Usuario retornado = this.service.encontrarUsuarioPorEmail(usuario.getEmail());
        assertEquals(retornado.getEmail(),usuario.getEmail());
    }

    @Test
    void quandoAdicionarEtiqueta_EntaoRetornaUsuarioEtiquetas(){
        Etiqueta etiqueta = Etiqueta.builder()
                                    .nome("tecnologia")
                                    .build();
        Usuario teste = this.service.encontrarUsuarioPorEmail(usuario.getEmail());
        UsuarioEtiquetasProjection retornado = this.service.adicionarEtiquetas(teste, List.of(etiqueta));
        teste = this.service.encontrarUsuarioPorEmail(usuario.getEmail());
        assertEquals(teste.getEtiquetas(), retornado.getEtiquetas());
    }

    @Test
    void quandoAcessarParametro_EntaoDeveCriarAcesso(){
        Usuario retornado = this.service.encontrarUsuarioPorEmail(usuario.getEmail());
        service.acessarEtiqueta(retornado, "tecnologia");
        assertTrue(retornado.getParametros() != null);
    }

    @Test
    void quandoAcessarParametro_EntaoDeveAumentarQuantidadeDeAcessos(){
        int num_anterior = acessosRepository.countAcessos("tecnologia").getCount();
        Usuario retornado = this.service.encontrarUsuarioPorEmail(usuario.getEmail());
        service.acessarEtiqueta(retornado, "tecnologia");
        int num_posterior = acessosRepository.countAcessos("tecnologia").getCount();
        assertTrue(num_anterior < num_posterior);
        
    }

    @Test
    void quandoEditarUsuario_EntaoRetornaUsuarioEditado(){
        Usuario encontrado = service.encontrarUsuarioPorEmail("gasa@gft.com");
        encontrado.setNome("Gael Santos");
        Usuario editado = service.editarUsuario(encontrado);
        assertEquals(encontrado.getUsuarioId(), editado.getUsuarioId());
    }

    @Test
    void quandoSalvarUsuarioComEmailExistente_EntaoLancaExcecao(){
        Usuario novo = Usuario.builder()
                              .email("gasa@gft.com")
                              .build();
        assertThrows(DuplicatedUniquePropertyException.class, ()-> {service.salvarUsuario(novo);});
    }

    @Test
    void quandoSalvarUsuarioComEmailExistente_EntaoLancaMensagem(){
        Usuario novo = Usuario.builder()
                              .email("gasa@gft.com")
                              .build();
        DuplicatedUniquePropertyException thrown = assertThrows(DuplicatedUniquePropertyException.class, 
                                () -> { service.salvarUsuario(novo);});
                     
        assertEquals("Email já cadastrado: gasa@gft.com", thrown.getMessage());
    }

    @Test
    void quandoExcluirUsuario_EntaoUsuarioDeveSerExcluido(){
        Long id = this.service.encontrarUsuarioPorEmail(usuario.getEmail()).getUsuarioId();
        service.excluirUsuario(id);
        assertTrue(service.encontrarUsuarioPorEmail(usuario.getEmail()) == null);
    }
}
