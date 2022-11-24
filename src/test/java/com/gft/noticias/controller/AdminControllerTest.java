package com.gft.noticias.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
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

    @BeforeAll
    static void setup(){
        
    }
    
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

    
}
