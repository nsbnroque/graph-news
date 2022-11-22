package com.gft.noticias.service;

import java.util.Collection;

import org.springframework.data.neo4j.core.Neo4jClient;
import org.springframework.stereotype.Service;

import com.gft.noticias.dto.ContagemAcessosDTO;
import com.gft.noticias.entity.Etiqueta;
import com.gft.noticias.entity.Usuario;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AcessosService {

    private final Neo4jClient neo4jClient;

    public Collection<ContagemAcessosDTO> contarAcessos(Etiqueta etiqueta) {
        return this.neo4jClient
                .query(""
                       + "MATCH (etiqueta:Etiqueta {nome : $nome}) "
                       +" MATCH ()-[r:ACESSOU]-(etiqueta)"
                       + " UNWIND r.acessos AS val"
                       +" RETURN etiqueta.nome AS n, COUNT(val) AS contagem ORDER BY contagem DESC"
                ) 
                .bind(etiqueta.getNome()).to("nome") 
                .fetchAs(ContagemAcessosDTO.class) 
                .mappedBy((typeSystem, record) -> new ContagemAcessosDTO(record.get("n").asString(),
                        record.get("contagem").asInt())) 
                .all(); 
    }

    public Collection<ContagemAcessosDTO> historicoAcessosUsuario(Usuario usuario) {
        return this.neo4jClient
                .query(""
                       + "MATCH (usuario:Usuario {email : $email})"
                       +" MATCH (usuario)-[r:ACESSOU]-(etiqueta)"
                       + "UNWIND r.acessos AS val"
                       +" RETURN etiqueta.nome AS n, COUNT(val) AS contagem ORDER BY contagem DESC"
                ) 
                .bind(usuario.getEmail()).to("email")
                .fetchAs(ContagemAcessosDTO.class) 
                .mappedBy((typeSystem, record) -> new ContagemAcessosDTO(record.get("n").asString(),
                        record.get("contagem").asInt())) 
                .all(); 
    }
    /* 
    private final AcessosRepository repository;



    public Page<Etiqueta> maioresAcessos(List<Etiqueta> etiquetas){
        List<MaisAcessadasView> etiquetasHistorico = new ArrayList<>();
        for (Etiqueta e: etiquetas){
            etiquetasHistorico.add(repository.countAcessos(e.getNome()));
        }
        return null;//etiquetasHistorico;
    }

    public List<MaisAcessadasView> etiquetasMaioresAcessos(List<Etiqueta> etiquetas){
        List<MaisAcessadasView> etiquetasHistorico = new ArrayList<>();
        for (Etiqueta e: etiquetas){
            etiquetasHistorico.add(repository.countAcessosEtiquetas(e.getNome()));
        }
        return etiquetasHistorico;
    }

    public ContagemAcessosDTO contagemAcessos(Etiqueta etiqueta){
        return repository.countAcessos(etiqueta.getNome());
    }
*/
    
}
