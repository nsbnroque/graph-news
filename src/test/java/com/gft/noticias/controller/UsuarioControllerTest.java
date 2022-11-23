package com.gft.noticias.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gft.noticias.entity.Etiqueta;
import com.gft.noticias.entity.Usuario;
import com.gft.noticias.service.UsuarioService;

@SpringBootTest
@AutoConfigureMockMvc
public class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioService usuarioService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup(){
        Usuario usuario = Usuario.builder()
                                 .nome("Gael dos Santos")
                                 .email("gasa@gft.com")
                                 .role("ROLE_USER")
                                 .senha("senha123")
                                 .build();
    }

    @Test
    void quandoAdicionarEtiqueta_EntaoEtiquetaDeveSerAdicionadaAoUsuario() throws Exception{
        Etiqueta etiqueta = Etiqueta.builder()
                                    .nome("esportes")
                                    .build();
        mockMvc.perform(post("/usuarios/1/etiquetas")
                .content(objectMapper.writeValueAsString(etiqueta))
                .contentType(MediaType.APPLICATION_JSON));
        /* 
        Usuario entrada = Usuario.builder()
                                 .nome("Gael dos Santos")
                                 .email("gasa@gft.com")
                                 .role("ROLE_USER")
                                 .senha("senha123")
                                 .build();
        when(usuarioService.salvarUsuario(entrada)).thenReturn(entrada);
        mockMvc.perform(post("/palavras")
                .content(objectMapper.writeValueAsString(output))
                .contentType(MediaType.APPLICATION_JSON));

    */
    }
    @Test
    void testAdicionarInteresses() {

    }

    @Test
    void testEncontrarUsuario() {

    }

    @Test
    void testRetornaNoticias() {

    }


}
