package com.tttn.spring.webshop.domain;

import javax.validation.constraints.NotNull;

public class CodeDomain {

    @NotNull
    private String macode;

    private String chitiet;

    @NotNull
    private int giamgia;

    @NotNull
    private int princemin;

    public int getPrincemin() {
        return princemin;
    }

    public CodeDomain setPrincemin(int princemin) {
        this.princemin = princemin;
        return this;
    }

    public String getMacode() {
        return macode;
    }

    public CodeDomain setMacode(String macode) {
        this.macode = macode;
        return this;
    }

    public String getChitiet() {
        return chitiet;
    }

    public CodeDomain setChitiet(String chitiet) {
        this.chitiet = chitiet;
        return this;
    }

    public int getGiamgia() {
        return giamgia;
    }

    public CodeDomain setGiamgia(int giamgia) {
        this.giamgia = giamgia;
        return this;
    }
}
