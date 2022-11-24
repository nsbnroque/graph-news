package com.gft.noticias.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gft.noticias.service.AdminService;

@SpringBootTest
@AutoConfigureMockMvc
public class AdminControllerTest {

    @Autowired
    AdminService service;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext context;

    @BeforeAll
    public void setup() {
        mockMvc = MockMvcBuilders
          .webAppContextSetup(context)
          .apply(springSecurity())
          .build();
    }

    @WithMockUser(roles = "ADMIN")
    @Test
    void quandoCadastrarAdministrador_EntaoRetornaAdministrador() throws Exception {

        mockMvc.perform(get("/cadastrar")).andExpect(status().isCreated());
    }

    @Test
    void quandoCadastrarUsuario_EntaoUsuarioCriado() throws Exception {
        mockMvc.perform(get("/cadastrar")).andExpect(status().isCreated());
    }

    @Test
    void quandoAcessarHistorico_EntaoRetornaHistoricodeAcessosPorUsuario() throws Exception {
        Long id = 1L;
        mockMvc.perform(get("/usuario/{id}")).andExpect(status().isOk());
    }

    @Test
    void quandoAcessarEtiquetasMaisAcessadas_EntaoRetornaOsMaioresAcessosDasEtiquetasSalvas() throws Exception{
        mockMvc.perform(get("/etiquetas")).andExpect(status().isOk());
    }

    @Test
    void quandoListarTendenciasDeAcesso_EntaoRetornaMaioresAcessosNasUltimas24Horas() throws Exception{
        mockMvc.perform(get("/trends")).andExpect(status().isOk());
    }

    @Test
    void quandoListarAcessos_EntaoRetornaTodosOsMaioresAcessos() throws Exception{
        mockMvc.perform(get("/acessos")).andExpect(status().isOk());
    }

    @Test
    void quandoEnviarEmailParaUsuario_EntaoEmailDeveSerEnviado(){

    }

    @Test
    void quandoEditarUsuario_EntaoUsuarioDeveSerEditado(){

    }

    
}
