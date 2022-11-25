package com.gft.noticias.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gft.noticias.dto.ConsultaUsuarioDTO;
import com.gft.noticias.dto.HistoricoDTO;
import com.gft.noticias.dto.SenhaForm;
import com.gft.noticias.entity.Etiqueta;
import com.gft.noticias.entity.Usuario;
import com.gft.noticias.exception.DuplicatedUniquePropertyException;
import com.gft.noticias.exception.EntityNotFoundException;
import com.gft.noticias.projections.MaisAcessadasView;
import com.gft.noticias.projections.ParametrosView;
import com.gft.noticias.projections.UsuarioEtiquetasProjection;
import com.gft.noticias.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository repository;
    private final EtiquetaService etiquetaService;
    private final AcessosService acessosService;
    private final PasswordEncoder encoder;

    @Transactional
    public Usuario salvarUsuario(Usuario usuario){
        if(!repository.findUsuarioByEmail(usuario.getEmail()).isEmpty()) {
            throw new DuplicatedUniquePropertyException("Email já cadastrado: " + usuario.getEmail());
        }
        usuario.setSenha(encoder.encode(usuario.getSenha()));
        usuario.setRole("ROLE_USER");
        return repository.save(usuario);
    }

    public Usuario encontrarUsuario(Long id){
        Optional<Usuario> encontrado = repository.findById(id);
        if(encontrado.isEmpty()){throw new EntityNotFoundException("Usuário não encontrado com o id: " + id);}
        return encontrado.get();
    }

    public Page<Usuario> listarUsuarios(Pageable pageable){
        return repository.findAll(pageable);
    }

    public void excluirUsuario(Long id){
        repository.deleteById(id);
    }

    public UsuarioEtiquetasProjection adicionarEtiquetas(Usuario usuario, List<Etiqueta> novasEtiquetas){
        String email = usuario.getEmail();
        for (Etiqueta etiqueta : novasEtiquetas){
            Etiqueta encontrada = etiquetaService.salvarEtiqueta(etiqueta);
            repository.matchRelationship(email, encontrada.getNome());
        }
        return repository.findByUsuarioId(usuario.getUsuarioId());
    }

    
    public void acessarEtiqueta(Usuario usuario, String etiquetaNome){
        LocalDate data = LocalDate.now();
        String now
            = data.toString();    
        if(etiquetaService.encontrarEtiqueta(etiquetaNome).isEmpty()){
            Etiqueta nova = Etiqueta.builder().nome(etiquetaNome).build();
            etiquetaService.salvarEtiqueta(nova);
        }     
        repository.accessEtiqueta(usuario.getEmail(),etiquetaNome,now);
    }

    public Usuario encontrarUsuarioPorEmail(String email) {
        Optional<Usuario> encontrado = repository.findUsuarioByEmail(email);
        if(encontrado.isEmpty()) {throw new UsernameNotFoundException(email);}
        return encontrado.get();
    }

    public Usuario editarUsuario(Usuario usuario) {
        if (usuario.getUsuarioId() != null){
            return repository.save(usuario);
        }
        return this.salvarUsuario(usuario);
    }

    public void excluirInteresse(Usuario usuario, Etiqueta etiqueta){
        String email = usuario.getEmail();
        String nome = etiqueta.getNome();
        repository.deleteRelationship(email,nome);
    }

    public List<Etiqueta> listarEtiquetas(Usuario usuario) {
        return usuario.getEtiquetas();
    }
/* 
    public List<MaisAcessadasView> maioresAcessos(Usuario usuario){
        return acessosService.;
    }
*/
    public List<HistoricoDTO> listarParametrosAcessados(Long id) {
        Usuario encontrado = repository.findById(id).get();
        return acessosService.historicoAcessos(encontrado);
    }

    public Usuario mudarSenha(Long id, SenhaForm form) {
        Optional<Usuario> encontrado = repository.findById(id);
        if(encontrado.isEmpty()){
            throw new EntityNotFoundException("Usuario não encontrado com o id: " + id);
        }
        Usuario editado = encontrado.get();
        editado.setSenha(form.getSenha());
        return repository.save(editado);
    }

    public List<Usuario> listar() {
        return repository.findAll();
    }
    

}
