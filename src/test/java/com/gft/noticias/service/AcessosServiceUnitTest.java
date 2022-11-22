package com.gft.noticias.service;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.gft.noticias.dto.ContagemAcessosDTO;
import com.gft.noticias.entity.Etiqueta;
import com.gft.noticias.entity.Usuario;

@SpringBootTest
public class AcessosServiceUnitTest {

    @Autowired
    AcessosService acessosService;

    @Autowired 
    UsuarioService usuarioService;

    private Usuario usuario = Usuario.builder().email("user@gft.com").nome("Usuario Sistema").build();
    private Etiqueta etiqueta = Etiqueta.builder().nome("teste").build();
    private Etiqueta etiqueta2 = Etiqueta.builder().nome("outra").build();

    @BeforeEach
    void setup(){
    usuarioService.salvarUsuario(usuario);
    usuarioService.acessarEtiqueta(usuario, etiqueta.getNome());
    usuarioService.acessarEtiqueta(usuario, etiqueta2.getNome());
        }
    
    @AfterEach
    void end(){
        Long id = usuarioService.encontrarUsuarioPorEmail("user@gft.com").getUsuarioId();
        usuarioService.excluirUsuario(id);
    }

    @Test
    void quandoChamarContarAcessos_EntaoRetornaNomeEQuantidadeDeAcessos(){
        Etiqueta etiqueta = Etiqueta.builder().nome("teste").build();
        //Collection<ContagemAcessosDTO> acessos = acessosService.contarAcessos(etiqueta);
       // System.out.println("-----------------------------------------------------------------------------" + acessos);
        System.out.println("-----------------------------------------------------------------------------" 
                            + acessosService.contarAcessos(etiqueta));
    }
    
    @Test
    void quandoChamarHistoricoDeAcessos_EntaoRetornaHistorico() {
        Usuario retornado = usuarioService.encontrarUsuarioPorEmail("user@gft.com");
        System.out.println(retornado.toString());
        List<ContagemAcessosDTO> historico = acessosService.historicoAcessos(retornado);
        System.out.println("--------------------------------------------------\n"
                            + historico.toString()
                            + "\n--------------------------------------------------\n");
        
    }

    @Test
    void testMaioresAcessos() {

    }
}
