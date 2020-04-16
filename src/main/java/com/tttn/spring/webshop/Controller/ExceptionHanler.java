package com.tttn.spring.webshop.Controller;

import com.tttn.spring.webshop.exception.SaveExceptionJPA;
import com.tttn.spring.webshop.service.ServiceRestfull;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ExceptionHanler  {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ServiceRestfull exceptionHanler(Exception ex){
        ServiceRestfull serviceRestfull=new ServiceRestfull();
        System.out.println("chay vao day");
        serviceRestfull.setMessage(ex.getMessage());
        return serviceRestfull;

    }
}
