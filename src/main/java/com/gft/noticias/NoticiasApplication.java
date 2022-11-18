package com.gft.noticias;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NoticiasApplication {
/* 
	@Bean
	public WebClient webClient(WebClient.Builder builder){
		return builder.baseUrl("https://apinoticias.tedk.com.br/api/")
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.build();
	}
*/
	public static void main(String[] args) {
		SpringApplication.run(NoticiasApplication.class, args);
       
       
	}

}
