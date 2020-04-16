package com.tttn.spring.webshop.domain;

public class CommentCustom {

    private String comment;

    private int idproduct;

    public int getIdproduct() {
        return idproduct;
    }

    public CommentCustom setIdproduct(int idproduct) {
        this.idproduct = idproduct;
        return this;
    }

    public String getComment() {
        return comment;
    }

    public CommentCustom setComment(String comment) {
        this.comment = comment;
        return this;
    }
}
