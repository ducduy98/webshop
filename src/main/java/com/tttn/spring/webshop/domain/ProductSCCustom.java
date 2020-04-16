package com.tttn.spring.webshop.domain;


import com.fasterxml.jackson.annotation.JsonBackReference;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Objects;


public class ProductSCCustom {

    @NotNull
    private String maphieu;

    @NotNull
    private Integer product;

    @NotNull
    private Integer size;

    @NotNull
    private Integer color;
    @NotNull
    private Integer number;
    @NotNull
    private Integer gia;


    public Integer getProduct() {
        return product;
    }


    public String getMaphieu() {
        return maphieu;
    }

    public ProductSCCustom setMaphieu(String maphieu) {
        this.maphieu = maphieu;
        return this;
    }

    public ProductSCCustom setProduct(Integer product) {
        this.product = product;
        return this;
    }

    public Integer getSize() {
        return size;
    }

    public ProductSCCustom setSize(Integer size) {
        this.size = size;
        return this;
    }

    public Integer getColor() {
        return color;
    }

    public ProductSCCustom setColor(Integer color) {
        this.color = color;
        return this;
    }

    public Integer getNumber() {
        return number;
    }

    public ProductSCCustom setNumber(Integer number) {
        this.number = number;
        return this;
    }

    public Integer getGia() {
        return gia;
    }

    public ProductSCCustom setGia(Integer gia) {
        this.gia = gia;
        return this;
    }
}
