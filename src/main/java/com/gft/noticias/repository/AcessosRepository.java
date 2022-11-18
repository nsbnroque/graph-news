package com.gft.noticias.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gft.noticias.entity.Acessos;
import com.gft.noticias.projections.MaisAcessadasView;

@Repository
public interface AcessosRepository extends Neo4jRepository<Acessos,Long> {

    @Query("MATCH (e:Etiqueta {nome:$nome}) MATCH ()-[r:ACESSOU]-(e) UNWIND r.acessos AS val RETURN e.nome, count(val)")
    MaisAcessadasView countAcessos(@Param("nome") String nome);

    @Query("MATCH (e:Etiqueta {nome:$nome}) MATCH ()-[r:TEM_INTERESSE_EM]-(e) UNWIND r.acessos AS val RETURN e.nome, count(val)")
    MaisAcessadasView countAcessosEtiquetas(@Param("nome") String nome);
    
}
