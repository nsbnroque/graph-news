package com.gft.noticias.projections;

import java.util.List;

public interface ParametrosView {
    List<Etiqueta> getAcessos();

    public interface Etiqueta {
        String getNome();
    }
    
}
