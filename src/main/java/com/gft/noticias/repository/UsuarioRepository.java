package com.gft.noticias.repository;

import java.util.List;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gft.noticias.entity.Etiqueta;
import com.gft.noticias.entity.Usuario;
import com.gft.noticias.projections.UsuarioEtiquetasProjection;

@Repository
public interface UsuarioRepository extends Neo4jRepository<Usuario,Long> {

    @Query(" MATCH (u: Usuario {email: $email}) MATCH (e:Etiqueta {nome: $etiqueta}) MERGE (u)-[r:ACESSOU]-(e) ON CREATE SET r.acessos = [$now] ON MATCH SET  r.acessos = CASE WHEN r.acessos IS NULL THEN [$now] ELSE r.acessos + [$now] END")
    void accessEtiqueta(@Param("email") String email, @Param("etiqueta") String etiqueta, @Param("now") String now);

    @Query("MATCH (u: Usuario {email: $email}) MATCH (e:Etiqueta {nome: $etiqueta}) MERGE (u)-[r:TEM_INTERESSE_EM]-(e) RETURN u")
    Usuario matchRelationship(@Param("email")String email,@Param("etiqueta") String etiqueta);

    Usuario findUsuarioByEmail(String email);

    UsuarioEtiquetasProjection findByUsuarioId(@Param("id") Long id);

    @Query("MATCH (u:Usuario{$usuario} -[r:TEM_INTERESSE_EM]->(e) RETURN e")
    List<Etiqueta> findFavorites(@Param("usuario")Usuario usuario);

    @Query("MATCH (u:Usuario{$usuario} -[r:TEM_INTERESSE_EM*2]->(e) RETURN e")
    List<Etiqueta> findRecommendations(@Param("usuario")Usuario usuario);

    @Query("MATCH (u)-[r:ACESSOU]->(e) RETURN e WHERE NOT EXISTS (u)-[:TEM_INTERESSE_EM]-(e) RETURN e")
    List<Etiqueta> findTrends(Usuario usuario);

    
}
