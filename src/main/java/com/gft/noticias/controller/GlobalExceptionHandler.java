package com.gft.noticias.controller;

import java.net.UnknownHostException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{
    
    @ExceptionHandler(UnknownHostException.class)
    public ResponseEntity<Object> handleUnknowHostException(Exception e, WebRequest request){
        String response = "Conexão com o banco de dados não encontrada.";
        return handleExceptionInternal(e, response, new HttpHeaders(), HttpStatus.SERVICE_UNAVAILABLE,request);
    }
}
