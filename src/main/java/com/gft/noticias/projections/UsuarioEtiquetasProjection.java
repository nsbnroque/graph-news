package com.gft.noticias.projections;

import java.util.List;

import com.gft.noticias.entity.Etiqueta;

public interface UsuarioEtiquetasProjection {
    String getNome();
    List<Etiqueta> getEtiquetas();
    
}
