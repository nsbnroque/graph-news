package com.gft.noticias.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gft.noticias.client.NoticiasResponse;
import com.gft.noticias.dto.ConsultaUsuarioDTO;
import com.gft.noticias.dto.UsuarioMapper;
import com.gft.noticias.entity.Etiqueta;
import com.gft.noticias.entity.Usuario;
import com.gft.noticias.projections.UsuarioEtiquetasProjection;
import com.gft.noticias.service.NoticiasService;
import com.gft.noticias.service.UsuarioService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService service;
    private final NoticiasService noticiasService;

    @PostMapping
    public ResponseEntity<Usuario> salvarUsuario(@RequestBody Usuario usuario){
        Usuario salvo = service.salvarUsuario(usuario);
        return ResponseEntity.ok(salvo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsultaUsuarioDTO> encontrarUsuario(@PathVariable Long id){
        Usuario encontrado = service.encontrarUsuario(id); 
        return ResponseEntity.ok(UsuarioMapper.fromEntity(encontrado));
    }

    @PostMapping("/{id}/etiquetas")
    public ResponseEntity<UsuarioEtiquetasProjection> adicionarInteresses(@PathVariable Long id, @RequestBody List<Etiqueta> etiquetas){
        Usuario encontrado = service.encontrarUsuario(id);
        return ResponseEntity.ok(service.adicionarEtiquetas(encontrado, etiquetas));
    }

    @GetMapping("/{id}/noticias/")
    public ResponseEntity<NoticiasResponse> retornaNoticias(@PathVariable Long id, @RequestParam("q") String etiquetaNome,@RequestParam("date") String data){
        Usuario encontrado = service.encontrarUsuario(id);
        //Etiqueta etiqueta = etiquetaService.encontrarEtiqueta(etiquetaNome);  
        service.acessarEtiqueta(encontrado, etiquetaNome); 
        NoticiasResponse noticias = noticiasService.obterNoticias(etiquetaNome, data);
        return ResponseEntity.ok(noticias);     
    }

    
}
