package com.gft.noticias.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.gft.noticias.entity.Etiqueta;
import com.gft.noticias.projections.MaisAcessadasView;
import com.gft.noticias.repository.AcessosRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AcessosService {

    private final AcessosRepository repository;

    public Page<Etiqueta> maioresAcessos(List<Etiqueta> etiquetas){
        List<MaisAcessadasView> etiquetasHistorico = new ArrayList<>();
        for (Etiqueta e: etiquetas){
            etiquetasHistorico.add(repository.countAcessos(e.getNome()));
        }
        return etiquetasHistorico;
    }

    public List<MaisAcessadasView> etiquetasMaioresAcessos(List<Etiqueta> etiquetas){
        List<MaisAcessadasView> etiquetasHistorico = new ArrayList<>();
        for (Etiqueta e: etiquetas){
            etiquetasHistorico.add(repository.countAcessosEtiquetas(e.getNome()));
        }
        return etiquetasHistorico;
    }

    
}
