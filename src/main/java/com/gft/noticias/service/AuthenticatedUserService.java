package com.gft.noticias.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.gft.noticias.entity.Usuario;
import com.gft.noticias.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticatedUserService {

  private final UsuarioRepository repository;

   public boolean hasId(Long id){          
      var principal =  SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      User retorno = (User) principal;
      Usuario usuario = repository.findUsuarioByEmail(retorno.getUsername()).get();
      return usuario.getUsuarioId().equals(id);
   }
    
}
