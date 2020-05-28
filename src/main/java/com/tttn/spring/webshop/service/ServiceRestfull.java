package com.tttn.spring.webshop.service;

import org.springframework.stereotype.Service;

@Service
public class ServiceRestfull {
    private Object data;
    private String message;

    public Object getData() {
        return data;
    }

    public ServiceRestfull setData(Object data) {
        this.data = data;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ServiceRestfull setMessage(String message) {
        this.message = message;
        return this;
    }

    @Override
    public String toString() {
        return "ServiceRestfull{" +
                "data=" + data +
                ", message='" + message + '\'' +
                '}';
    }
}
