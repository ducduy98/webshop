package com.tttn.spring.webshop.domain;

public class Search {
    private String tukhoa;

    private int status;

    public String getTukhoa() {
        return tukhoa;
    }

    public Search setTukhoa(String tukhoa) {
        this.tukhoa = tukhoa;
        return this;
    }

    public int getStatus() {
        return status;
    }

    public Search setStatus(int status) {
        this.status = status;
        return this;
    }
}
