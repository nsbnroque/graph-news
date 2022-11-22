package com.gft.noticias.service;

import java.util.Collection;

import org.junit.jupiter.api.BeforeAll;
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

    private Usuario usuario;
    private Etiqueta etiqueta;

    @BeforeAll
    private void setup(){
        usuario = Usuario.builder().email("user@gft.com").nome("Usuario Sistema").build();
        etiqueta = Etiqueta.builder().nome("teste").build();

    }
    
    @Test
    void quandoChamarContarAcessos_EntaoRetornaNomeEQuantidadeDeAcessos(){
        Etiqueta etiqueta = Etiqueta.builder().nome("tecnologia").build();
        Collection<ContagemAcessosDTO> acessos = acessosService.contarAcessos(etiqueta);
        System.out.println(acessos);
    }
    
    @Test
    void quandoChamarHistoricoDeAcessos_EntaoRetornaHistorico() {
        Usuario retornado = usuarioService.encontrarUsuarioPorEmail("gasa@gft.com");
        System.out.println(retornado.toString());
        //Collection<ContagemAcessosDTO> historico = acessosService.historicoAcessosUsuario(retornado);
        //System.out.println(historico);
        
    }

    @Test
    void testMaioresAcessos() {

    }
}
