package com.gft.noticias.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Node;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Node
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Etiqueta {
    @Id
    @GeneratedValue
    private Long etiquetaId;

    private String nome;

}
