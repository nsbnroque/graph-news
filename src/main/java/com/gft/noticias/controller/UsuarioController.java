package com.gft.noticias.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gft.noticias.client.Noticias;
import com.gft.noticias.dto.ConsultaUsuarioDTO;
import com.gft.noticias.dto.UsuarioForm;
import com.gft.noticias.dto.UsuarioMapper;
import com.gft.noticias.entity.Etiqueta;
import com.gft.noticias.entity.Usuario;
import com.gft.noticias.projections.UsuarioEtiquetasProjection;
import com.gft.noticias.service.AuthenticatedUserService;
import com.gft.noticias.service.NoticiasService;
import com.gft.noticias.service.UsuarioService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService service;
    private final NoticiasService noticiasService;
    private final AuthenticatedUserService authenticatedUserService;


    @GetMapping("/{id}")
    @PreAuthorize("@authenticatedUserService.hasId(#id)")
    public ResponseEntity<ConsultaUsuarioDTO> encontrarUsuario(@PathVariable("id") Long id){
        Usuario encontrado = service.encontrarUsuario(id); 
        return ResponseEntity.ok(UsuarioMapper.fromEntity(encontrado));
    }

    @PostMapping("/{id}/etiquetas")
    @PreAuthorize("@authenticatedUserService.hasId(#id)")
    public ResponseEntity<UsuarioEtiquetasProjection> adicionarInteresses(@PathVariable Long id, @RequestBody List<Etiqueta> etiquetas){
        Usuario encontrado = service.encontrarUsuario(id);
        return ResponseEntity.ok(service.adicionarEtiquetas(encontrado, etiquetas));
    }

    @PutMapping("/{id}/editar")
    @PreAuthorize("@authenticatedUserService.hasId(#id)")
    public ResponseEntity<ConsultaUsuarioDTO> editarUsuario(@PathVariable Long id, @RequestBody UsuarioForm form){
        Usuario retorno = service.editarUsuario(UsuarioMapper.toEntity(id,form));
        return ResponseEntity.ok(UsuarioMapper.fromEntity(retorno));
    }

    @GetMapping("/{id}/noticias/")
    @PreAuthorize("@authenticatedUserService.hasId(#id)")
    public ResponseEntity<List<Noticias>> retornaNoticias(@PathVariable Long id, @RequestParam("q") String etiquetaNome,@RequestParam(name="date", required = false) String data){
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
        if (data == null) {
            Usuario encontrado = service.encontrarUsuario(id);
            LocalDate localDate = LocalDate.now();
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
