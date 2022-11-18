package com.gft.noticias.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.gft.noticias.entity.Etiqueta;
import com.gft.noticias.exception.EntityNotFoundException;
import com.gft.noticias.repository.EtiquetaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EtiquetaService {

    private final EtiquetaRepository repository;

    public Etiqueta salvarEtiqueta(Etiqueta etiqueta){
        Optional<Etiqueta> encontrada = repository.findOneByNomeIgnoreCase(etiqueta.getNome());
        if(encontrada.isEmpty()){
            etiqueta.setNome(etiqueta.getNome().toLowerCase());
            return repository.save(etiqueta);
        } else{
            return encontrada.get();
        }       
    }

    public Etiqueta encontrarEtiquetaPeloId(Long id){
        Optional<Etiqueta> encontrada = repository.findById(id);
        if(encontrada.isEmpty()){throw new EntityNotFoundException("Etiqueta não encontrada com o id: " + id);}
        return encontrada.get();
    }

    public Etiqueta encontrarEtiqueta(String nome){
        Optional<Etiqueta> encontrada = repository.findOneByNomeIgnoreCase(nome);
        if(encontrada.isEmpty()){throw new EntityNotFoundException("Etiqueta não encontrada com o nome: " + nome);}
        return encontrada.get();
    }

    public List<Etiqueta> listarTodas(){
        return repository.findAll();
    }

    public void excluirEtiqueta(Long id){
        repository.deleteById(id);
    }

    public void excluirEtiquetaPeloNome(String nome){
        Optional<Etiqueta> encontrada = repository.findOneByNomeIgnoreCase(nome);
        if(encontrada.isEmpty()){throw new EntityNotFoundException("Etiqueta não encontrada com o nome: " + nome);}
        Long id = encontrada.get().getEtiquetaId();
        repository.deleteById(id);
    }

    
}
