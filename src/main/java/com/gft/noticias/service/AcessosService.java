package com.gft.noticias.service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.neo4j.core.Neo4jClient;
import org.springframework.stereotype.Service;

import com.gft.noticias.dto.ContagemAcessosDTO;
import com.gft.noticias.dto.EtiquetaDTO;
import com.gft.noticias.entity.Etiqueta;
import com.gft.noticias.entity.Usuario;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AcessosService {

    private final Neo4jClient neo4jClient;

    public Collection<ContagemAcessosDTO> fetchAcessos(Etiqueta etiqueta) {
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

    public List<ContagemAcessosDTO> contarAcessos(Etiqueta etiqueta){
        return this.fetchAcessos(etiqueta).stream().collect(Collectors.toList());
    }

    public Collection<ContagemAcessosDTO> fetchHistorico(Usuario usuario) {
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

    public List<ContagemAcessosDTO> historicoAcessos(Usuario usuario){
        return this.fetchHistorico(usuario).stream().collect(Collectors.toList());
    }

    public Collection<EtiquetaDTO> fetchRecommendations(Usuario usuario){
        return this.neo4jClient
               .query("").bind(usuario.getEmail()).to("email")
               .fetchAs(EtiquetaDTO.class)
               .mappedBy((typeSystem,record) -> new EtiquetaDTO(record.get("nome").asString()))
               .all();
    }

    public Collection<EtiquetaDTO> fetchTrends(String data){
        return this.neo4jClient
               .query("").bind(data).to("data")
               .fetchAs(EtiquetaDTO.class)
               .mappedBy((typeSystem,record) -> new EtiquetaDTO(record.get("nome").asString()))
               .all();
    }

    
    
}
