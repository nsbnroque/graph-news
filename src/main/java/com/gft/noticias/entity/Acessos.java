package com.gft.noticias.entity;

import java.util.List;

import org.springframework.data.neo4j.core.schema.RelationshipId;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@RelationshipProperties
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Acessos {

    @RelationshipId
    Long acessosId;

    @TargetNode
    private Etiqueta etiqueta;

    private List<String> acessos;
}
