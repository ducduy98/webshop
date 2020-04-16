package com.tttn.spring.webshop.config;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;

@ControllerAdvice
public class CustomExeptionHanlerApi extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex
            , HttpHeaders headers, HttpStatus status
            , WebRequest request) {
        System.out.println("chay vao trong custom handle exception");
        LinkedHashMap<String,String> listLoi=new LinkedHashMap<>();
        SimpleDateFormat format=new SimpleDateFormat("dd - MM - YYYY  hh:mm:ss");
        listLoi.put("timestamp", format.format(new Date()));
        listLoi.put("status", String.valueOf(status.value()));
        listLoi.put("message","khong nhap du cac truong");

        return new ResponseEntity<>(listLoi,headers,status);
    }
}
