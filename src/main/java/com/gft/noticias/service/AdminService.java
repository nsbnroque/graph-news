package com.gft.noticias.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gft.noticias.entity.Admin;
import com.gft.noticias.entity.Etiqueta;
import com.gft.noticias.projections.MaisAcessadasView;
import com.gft.noticias.repository.AdminRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AdminRepository repository;
    private final EtiquetaService etiquetaService;
    private final AcessosService acessosService;

    public Admin salvarAdmin(Admin admin){
        return repository.save(admin);
    }

    List<MaisAcessadasView> etiquetasTrends() {
        List<Etiqueta> todas = etiquetaService.listarTodas();
        return acessosService.maioresAcessos(todas);
    }


    void etiquetasMaisAcessadas(){

    }

    void enviarEmail(){

    }

    public void excluirAdmin(Long id) {
    }
}
