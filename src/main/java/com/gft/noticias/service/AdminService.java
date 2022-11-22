package com.gft.noticias.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.gft.noticias.entity.Admin;
import com.gft.noticias.exception.EntityNotFoundException;
import com.gft.noticias.repository.AdminRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AdminRepository repository;


    public Admin salvarAdmin(Admin admin){
        return repository.save(admin);
    }

    public List<Admin> listar(){
        return repository.findAll();
    }

    public Admin findByEmail(String email){
        return repository.findByEmail(email);
    }

    public Admin editar(Long id, Admin admin){
        Optional<Admin> encontrado = repository.findById(id);
        if (encontrado.isEmpty()){ 
            throw new EntityNotFoundException("Admin não encontrado com o id: " + id);}
            Admin retornado = encontrado.get();
            retornado.setEmail(admin.getEmail());
            retornado.setSenha(admin.getSenha());
        return repository.save(retornado);
    }

    public void excluirAdmin(Long id) {
        repository.deleteById(id);
    }
}
