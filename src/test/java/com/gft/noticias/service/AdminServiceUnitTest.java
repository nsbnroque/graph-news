package com.gft.noticias.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.gft.noticias.entity.Admin;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AdminServiceUnitTest {

    @Autowired
    private AdminService adminService;

    private static Admin admin;

    @BeforeAll
    public static void setup(){
        admin = Admin.builder().nome("Administrador").email("admin@email.com").build();
    }

    @Test
    @Order(1)
    void quandoSalvarAdministrador_EntaoRetornaAdministrador() {
        Admin salvo = adminService.salvarAdmin(admin);
        assertEquals("Administrador", salvo.getNome());
    }

    @Test
    @Order(2)
    void quandoEncontrarPorEmail_EntaoRetornaAdmin() {
        Admin retornado = adminService.findByEmail("admin@email.com");
        assertEquals("admin@email.com", retornado.getEmail());
    }

    @Test
    @Order(3)
    void quandoAcessarRole_EntaoRetornaAdmin() {
        Admin retornado = adminService.findByEmail("admin@email.com");
        assertEquals("ROLE_ADMIN", retornado.getRole());
    }

    @Test
    @Order(4)
    void quandoListAdmin_EntaoRetornaListaAdmin() {
        Admin retornado = adminService.findByEmail("admin@email.com");
        List<Admin> lista = adminService.listar();
        assertTrue(lista != null);
        assertTrue(lista.contains(retornado));
    }

    @Test
    void quandoTrocarSenha_EntaoSomenteSenhaSeraAlterada() {
        Admin retornado = adminService.findByEmail("admin@email.com");
        Admin editado = adminService.trocarSenha(retornado.getAdminId(), "novaSenha");

        assertEquals(retornado.getRole(), editado.getRole());
        assertEquals(retornado.getEmail(), editado.getEmail());
        assertEquals(retornado.getNome(), editado.getNome());
    }

    @Test
    void testExcluirAdmin() {

    }


}
