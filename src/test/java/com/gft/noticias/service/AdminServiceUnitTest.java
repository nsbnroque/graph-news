package com.gft.noticias.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AdminServiceUnitTest {

    @Autowired
    private AdminService adminService;

    @Test
    void testEnviarEmail() {
        adminService.enviarEmail();
    }

    @Test
    void testEtiquetasMaisAcessadas() {

    }

    @Test
    void testEtiquetasTrends() {

    }

    @Test
    void testExcluirAdmin() {

    }

    @Test
    void testSalvarAdmin() {

    }
}
