package com.gft.noticias.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gft.noticias.client.Noticias;
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

    /* 
    @PostMapping
    public ResponseEntity<Usuario> salvarUsuario(@RequestBody Usuario usuario){
        Usuario salvo = service.salvarUsuario(usuario);
        return ResponseEntity.ok(salvo);
    }
    */

    @GetMapping("/{id}")
    //@PreAuthorize("#id == authentication.usuarioId")
    public ResponseEntity<ConsultaUsuarioDTO> encontrarUsuario(@PathVariable("id") Long id){
        Usuario encontrado = service.encontrarUsuario(id); 
        return ResponseEntity.ok(UsuarioMapper.fromEntity(encontrado));
    }

    @PostMapping("/{id}/etiquetas")
    public ResponseEntity<UsuarioEtiquetasProjection> adicionarInteresses(@PathVariable Long id, @RequestBody List<Etiqueta> etiquetas){
        Usuario encontrado = service.encontrarUsuario(id);
        return ResponseEntity.ok(service.adicionarEtiquetas(encontrado, etiquetas));
    }

    @GetMapping("/{id}/noticias/")
    public ResponseEntity<List<Noticias>> retornaNoticias(@PathVariable Long id, @RequestParam("q") String etiquetaNome,@RequestParam(name="date", required = false) String data){
        if (data == null) {
            Usuario encontrado = service.encontrarUsuario(id);
            LocalDate localDate = LocalDate.now();//For reference
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String dataString = localDate.format(formatter);
            service.acessarEtiqueta(encontrado, etiquetaNome); 
            List<Noticias> noticias = noticiasService.listarNoticias(etiquetaNome, dataString);
        return ResponseEntity.ok(noticias);    

        }
           
        Usuario encontrado = service.encontrarUsuario(id);
        service.acessarEtiqueta(encontrado, etiquetaNome); 
        List<Noticias> noticias = noticiasService.listarNoticias(etiquetaNome, data);
        return ResponseEntity.ok(noticias);     
    }

    
}
