package com.gft.noticias.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistroUsuarioDTO {
    private String nome;
    private String email;
}
