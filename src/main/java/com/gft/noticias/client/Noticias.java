package com.gft.noticias.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Noticias {

    private String title;
    private String link;
    private String date;

}
