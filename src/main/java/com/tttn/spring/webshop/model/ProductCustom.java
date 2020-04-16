package com.tttn.spring.webshop.model;

import javax.validation.constraints.NotBlank;

public class ProductCustom {

    @NotBlank(message = "khong duoc de trong truong")
    private int prince;
    @NotBlank(message = "khong duoc de trong truong")
    private String product;
    @NotBlank(message = "khong duoc de trong truong")
    private int idProduct;
    @NotBlank(message = "khong duoc de trong truong")
    private String image;

    @NotBlank(message = "khong duoc de trong truong")
    private int category;

    @NotBlank(message = "khong duoc de trong truong")
    private String desc;


    public String getDesc() {
        return desc;
    }

    public ProductCustom setDesc(String desc) {
        this.desc = desc;
        return this;
    }

    public String getProduct() {
        return product;
    }

    public ProductCustom setProduct(String product) {
        this.product = product;
        return this;
    }

    public String getImage() {
        return image;
    }

    public ProductCustom setImage(String image) {
        this.image = image;
        return this;
    }

    public int getCategory() {
        return category;
    }

    public ProductCustom setCategory(int category) {
        this.category = category;
        return this;
    }

    public int getPrince() {
        return prince;
    }

    public ProductCustom setPrince(int price) {
        this.prince = price;
        return this;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public ProductCustom setIdProduct(int idProduct) {
        this.idProduct = idProduct;
        return this;
    }
}
