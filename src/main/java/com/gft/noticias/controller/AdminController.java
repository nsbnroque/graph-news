package com.gft.noticias.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gft.noticias.entity.Usuario;
import com.gft.noticias.service.AdminService;
import com.gft.noticias.service.UsuarioService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final UsuarioService usuarioService;
    private final AdminService adminService;
    //private final AcessosService acessosService;
    //private final EtiquetaService etiquetaService;



    @GetMapping("/usuarios")
    public ResponseEntity<Page<Usuario>> listarUsuarios(Pageable pageable){
        return ResponseEntity.ok(usuarioService.listarUsuarios(pageable));
    }

    @GetMapping("usuarios/{id}")
    public ResponseEntity<Usuario> encontrarUsuario(@PathVariable Long id){     
        return ResponseEntity.ok(usuarioService.encontrarUsuario(id));
    }

    @GetMapping("/etiquetas")
    public ResponseEntity<String> listarEtiquetas(Pageable pageable){
        //acessosService.maioresAcessos(etiquetaService.listarTodas())
        return ResponseEntity.ok("OK");
    }

    @GetMapping("/trends")
    public ResponseEntity<String> listarTendencias(Pageable pageable){
        //acessosService.maioresAcessos(etiquetaService.listarTodas())
        return ResponseEntity.ok("OK");
    }

    @GetMapping("/acessos")
    public ResponseEntity<String> listarAcessos(Pageable pageable){
        //acessosService.maioresAcessos(etiquetaService.listarTodas())
        return ResponseEntity.ok("Ok");
    }

    @PostMapping("/registrar")
    public ResponseEntity<Usuario> registrarUsuario(@RequestParam("role") String role,
                                   @RequestBody(required = false) Usuario usuario){
        Usuario salvo = usuarioService.salvarUsuario(usuario);
        return ResponseEntity.ok(salvo);
    }

    @DeleteMapping("/usuarios/excluir/{id}")
    public ResponseEntity<String> excluirUsuario(@PathVariable Long id){
        usuarioService.excluirUsuario(id);
        return ResponseEntity.ok("Usuário excluído com sucesso.");
    }

    @DeleteMapping("/admin/excluir/{id}")
    public ResponseEntity<String> excluirAdmin(@PathVariable Long id){
        adminService.excluirAdmin(id);
        return ResponseEntity.ok("Usuário excluído com sucesso.");
    }


}
