package com.gft.noticias.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.gft.noticias.entity.Etiqueta;
import com.gft.noticias.entity.Usuario;
import com.gft.noticias.exception.DuplicatedUniquePropertyException;
import com.gft.noticias.projections.UsuarioEtiquetasProjection;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UsuarioServiceUnitTest {

    @Autowired
    private UsuarioService service;


    private static Usuario usuario;

    @BeforeAll
    static void setup(){
        usuario = Usuario.builder()
                         .nome("Gael dos Santos")
                         .email("gasa@gft.com")
                         .senha("senha123")
                         .build();                      
    }


    @Test
    @Order(1)
    void quandoEncontrarUsuarioPeloId_EntaoRetornaUsuario(){
        Usuario salvo = this.service.salvarUsuario(usuario);
        Usuario retornado = this.service.encontrarUsuario(salvo.getUsuarioId());
        assertEquals(retornado.getEmail(), usuario.getEmail());
    }

    @Test
    @Order(2)
    void quandoEncontrarUsuarioPeloEmail_EntaoRetornaUsuario(){
        Usuario retornado = this.service.encontrarUsuarioPorEmail(usuario.getEmail());
        assertEquals(retornado.getEmail(),usuario.getEmail());
    }

    @Test
    @Order(3)
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
    @Order(4)
    void quandoAcessarParametro_EntaoDeveCriarAcesso(){
        Usuario retornado = this.service.encontrarUsuarioPorEmail(usuario.getEmail());
        service.acessarEtiqueta(retornado, "tecnologia");
        assertTrue(retornado.getParametros() != null);
    }

    @Test 
    @Order(5)
    void quandoAcessarParametro_EntaoParametroDeveSerCriado(){
        Usuario retornado = this.service.encontrarUsuarioPorEmail(usuario.getEmail());
        service.acessarEtiqueta(retornado, "futebol");
        assertTrue(retornado.getParametros() != null);
    }

    @Test
    @Order(6)
    void quandoSalvarUsuario_EntaoAdicionaPropriedadeRole(){
        Usuario retornado = this.service.encontrarUsuarioPorEmail(usuario.getEmail());
        assertEquals("ROLE_USER", retornado.getRole());      
    }

    @Test
    @Order(7)
    void quandoEditarUsuario_EntaoRetornaUsuarioEditado(){
        Usuario encontrado = service.encontrarUsuarioPorEmail("gasa@gft.com");
        encontrado.setNome("Gael Santos");
        Usuario editado = service.editarUsuario(encontrado.getUsuarioId(),encontrado);
        assertEquals(encontrado.getUsuarioId(), editado.getUsuarioId());
    }

    @Test
    @Order(8)
    void quandoSalvarUsuarioComEmailExistente_EntaoLancaExcecao(){
        Usuario novo = Usuario.builder()
                              .email("gasa@gft.com")
                              .build();
        assertThrows(DuplicatedUniquePropertyException.class, ()-> {service.salvarUsuario(novo);});
    }

    @Test
    @Order(9)
    void quandoSalvarUsuarioComEmailExistente_EntaoLancaMensagem(){
        Usuario novo = Usuario.builder()
                              .email("gasa@gft.com")
                              .build();
        DuplicatedUniquePropertyException thrown = assertThrows(DuplicatedUniquePropertyException.class, 
                                () -> { service.salvarUsuario(novo);});
                     
        assertEquals("Email já cadastrado: gasa@gft.com", thrown.getMessage());
    }

    @Test
    @Order(10)
    void quandoSalvarUsuario_EntaoSenhaDeveSerCriptografada(){
        Usuario encontrado = service.encontrarUsuarioPorEmail("gasa@gft.com");
        assertFalse(encontrado.getSenha() == "senha123");
    }

    @Order(11)
    void quandoAcessarParametro_EntaoDeveAumentarQuantidadeDeAcessos(){
        
    }

    @Test
    @Order(50)
    void quandoExcluirUsuario_EntaoUsuarioDeveSerExcluido(){
        Long id = service.encontrarUsuarioPorEmail(usuario.getEmail()).getUsuarioId();
        service.excluirUsuario(id);
        assertThrows(UsernameNotFoundException.class, ()-> {service.encontrarUsuarioPorEmail(usuario.getEmail());});
    }
}
