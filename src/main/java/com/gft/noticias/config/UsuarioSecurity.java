package com.gft.noticias.config;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component("usuarioSecurity")
public class UsuarioSecurity {
    public boolean hasUserId(Authentication authentication, Long userId) {
        if (authentication.getPrincipal().getUsername()
        // do your check(s) here
    }
    
}
