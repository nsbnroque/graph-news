package com.gft.noticias.repository;

import java.util.Optional;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import com.gft.noticias.entity.Etiqueta;

@Repository
public interface EtiquetaRepository extends Neo4jRepository<Etiqueta,Long> {

    Optional<Etiqueta> findOneByNomeIgnoreCase(String nome);
    
}
