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

import com.gft.noticias.entity.Admin;
import com.gft.noticias.repository.AdminRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminDetailsServiceImpl {

    private final AdminRepository adminRepository;
/* 
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Admin admin = buscarAdminPeloUsername(email);
        String authorityString = admin.getRole();
        System.out.println(authorityString);
        GrantedAuthority authority = new SimpleGrantedAuthority(authorityString);
        return new User(admin.getEmail(), admin.getSenha(), List.of(authority));
    }

    private Admin buscarAdminPeloUsername(String username) {
        Optional<Admin> optional = this.adminRepository.findByEmail(username);
        if(optional.isEmpty()) {
            throw new UsernameNotFoundException("Administrador não encontrado");
        }
        return optional.get();
    }
    */
}
