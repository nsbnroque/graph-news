package com.gft.noticias.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistroForm {
    private String nome;
    private String email;  
    private String senha;
    private String role;
}
