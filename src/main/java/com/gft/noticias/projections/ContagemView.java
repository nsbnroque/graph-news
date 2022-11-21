package com.gft.noticias.projections;

public interface ContagemView {
    Etiqueta getEtiqueta();
    int getCount();

public interface Etiqueta{
    String getNome();
}

}
