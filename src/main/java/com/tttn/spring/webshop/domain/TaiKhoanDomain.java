package com.tttn.spring.webshop.domain;

public class TaiKhoanDomain {

    private String id;
    private String pass;

    public String getId() {
        return id;
    }

    public TaiKhoanDomain setId(String id) {
        this.id = id;
        return this;
    }

    public String getPass() {
        return pass;
    }

    public TaiKhoanDomain setPass(String pass) {
        this.pass = pass;
        return this;
    }
}
