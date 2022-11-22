package com.gft.noticias.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.gft.noticias.entity.Usuario;
import com.gft.noticias.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService{
    private final UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = buscarUsuarioPeloUsername(email);
        String authorityString = usuario.getRole();
        GrantedAuthority authority = new SimpleGrantedAuthority(authorityString);
        return new User(usuario.getEmail(), usuario.getSenha(), List.of(authority));
    }

    private Usuario buscarUsuarioPeloUsername(String username) {
        Optional<Usuario> optional = this.usuarioRepository.findUsuarioByEmail(username);
        if(optional.isEmpty()) {
            throw new UsernameNotFoundException("Usuario não encontrado");
        }
        return optional.get();
    }
    
}
