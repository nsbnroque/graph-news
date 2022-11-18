package com.gft.noticias.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.gft.noticias.entity.Etiqueta;
import com.gft.noticias.entity.Usuario;
import com.gft.noticias.repository.UsuarioRepository;

@SpringBootTest
public class UsuarioServiceTest {
    @Autowired
    private UsuarioService service;

    @MockBean
    private UsuarioRepository repository;

    @BeforeEach
    void setup(){

        Usuario usuario = Usuario.builder()
                                 .nome("Gael dos Santos")
                                 .build();
        Optional<Usuario> retorno = Optional.of((Usuario) usuario);

        when(repository.findById(1L)).thenReturn(retorno);
    }


    @Test
    void quandoEncontrarUsuarioPeloId_EntaoRetornaUsuario() {
        Long id = 1L;
        Usuario encontrado = service.encontrarUsuario(id);
        assertEquals("Gael dos Santos", encontrado.getNome());

    }

    @Test
    void quandoAdicionarEtiquetas_EntaoRetornaUsuarioComEtiqueta(){
        Etiqueta nova = Etiqueta.builder()
                                .nome("tecnologia")
                                .build();
        Usuario encontrado = service.encontrarUsuario(1L);
        when(repository.matchRelationship(encontrado.getEmail(),nova.getNome())).thenReturn(encontrado);
        
    }

    @Test
    void testExcluirUsuario() {

    }

    @Test
    void testListarUsuarios() {

    }

    @Test
    void testSalvarUsuario() {

    }
}
