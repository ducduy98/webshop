package com.tttn.spring.webshop.model;

public class SexCategoryCustom {

    private int idSex;

    private int idCategory;

    private String sex;

    private String category;

    public int getIdSex() {
        return idSex;
    }

    public SexCategoryCustom setIdSex(int idSex) {
        this.idSex = idSex;
        return this;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public SexCategoryCustom setIdCategory(int idCategory) {
        this.idCategory = idCategory;
        return this;
    }

    public String getSex() {
        return sex;
    }

    public SexCategoryCustom setSex(String sex) {
        this.sex = sex;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public SexCategoryCustom setCategory(String category) {
        this.category = category;
        return this;
    }
}
