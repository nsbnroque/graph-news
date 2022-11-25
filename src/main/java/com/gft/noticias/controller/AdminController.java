package com.gft.noticias.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gft.noticias.dto.ConsultaUsuarioDTO;
import com.gft.noticias.dto.ContagemAcessosDTO;
import com.gft.noticias.dto.HistoricoDTO;
import com.gft.noticias.dto.UsuarioMapper;
import com.gft.noticias.entity.Usuario;
import com.gft.noticias.service.AcessosService;
import com.gft.noticias.service.AdminService;
import com.gft.noticias.service.UsuarioService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final UsuarioService usuarioService;
    private final AdminService adminService;
    private final AcessosService acessosService;
    //private final EtiquetaService etiquetaService;



    @GetMapping("/usuarios")
    public ResponseEntity<Page<ConsultaUsuarioDTO>> listarUsuarios(@PageableDefault Pageable pageable){
        return ResponseEntity.ok(
            usuarioService.listarUsuarios(pageable).map(UsuarioMapper::fromEntity));
    }

    @GetMapping("usuarios/{id}")
    public ResponseEntity<Usuario> encontrarUsuario(@PathVariable Long id){     
        return ResponseEntity.ok(usuarioService.encontrarUsuario(id));
    }

    @GetMapping("usuarios/{id}/parametros")
    public ResponseEntity<List<HistoricoDTO>> listarParametrosAcessados(@PathVariable Long id){
        Usuario usuario = usuarioService.encontrarUsuario(id);
        return ResponseEntity.ok(acessosService.historicoAcessos(usuario));
    }

    @GetMapping("/etiquetas")
    public ResponseEntity<String> listarEtiquetas(Pageable pageable){
        return ResponseEntity.ok("OK");
    }

    @GetMapping("/trends")
    public ResponseEntity<String> listarTendencias(Pageable pageable){
        //acessosService.maioresAcessos(etiquetaService.listarTodas())
        return ResponseEntity.ok("OK");
    }

    @GetMapping("/acessos")
    public ResponseEntity<List<ContagemAcessosDTO>> listarAcessos(){
        return ResponseEntity.ok(acessosService.contarAcessos());
    }

    @PostMapping("/registrar")
    public ResponseEntity<Usuario> registrarUsuario(
                                   @RequestBody Usuario usuario){
        Usuario salvo = usuarioService.salvarUsuario(usuario);
        return ResponseEntity.ok(salvo);
    }

    @PutMapping
    public ResponseEntity<Usuario> editarUsuario(@RequestBody Usuario usuario){
        Usuario editado = usuarioService.editarUsuario(usuario);
        return ResponseEntity.ok(editado);
    }

    @DeleteMapping("/usuarios/excluir/{id}")
    public ResponseEntity<String> excluirUsuario(@PathVariable Long id){
        usuarioService.excluirUsuario(id);
        return ResponseEntity.ok("Usuário excluído com sucesso.");
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<String> excluirAdmin(@PathVariable Long id){
        try{
        adminService.excluirAdmin(id);
        } catch (Exception e){
            ResponseEntity.notFound();
        }
        return ResponseEntity.ok("Administrador excluído com sucesso.");
    }


}
