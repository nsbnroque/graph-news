package com.gft.noticias.client;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoticiasResponse {

  private String count;
  private List<Noticias> list;



  
}
