package com.gft.noticias.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConsultaUsuarioDTO {
    private Long id;
    private String nome;
    private String email;
}
