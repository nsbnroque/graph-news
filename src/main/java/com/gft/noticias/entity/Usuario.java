package com.gft.noticias.entity;

import java.util.List;

import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Node
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Usuario {
    @Id
    @GeneratedValue
    private Long usuarioId;
    private String nome;
    private String email;
    private String senha;
    private String role;

    @Relationship(type="TEM_INTERESSE_EM",direction = Relationship.Direction.OUTGOING )
    List<Etiqueta> etiquetas;

    @Relationship(type="ACESSOU")
    List<Acessos> parametros;

    
}
