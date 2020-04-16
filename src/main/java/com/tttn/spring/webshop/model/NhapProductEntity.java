package com.tttn.spring.webshop.model;


import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name="nhapproduct")
public class NhapProductEntity {

    private int idnhapproduct;
    private int soluong;

    private int gianhap;

    private NhapHangEntity nhapHangEntityByIdnhaphang;

    private ProductSizeColorEntity productSizeColorEntity;

    @Basic
    @Column(name = "idnhapproduct")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getIdnhapproduct() {
        return idnhapproduct;
    }

    public NhapProductEntity setIdnhapproduct(int idnhapproduct) {
        this.idnhapproduct = idnhapproduct;
        return this;
    }

    @Basic
    @Column(name = "soluong")
    public int getSoluong() {
        return soluong;
    }

    public NhapProductEntity setSoluong(int soluong) {
        this.soluong = soluong;
        return this;
    }

    @Basic
    @Column(name = "gianhap")
    public int getGianhap() {
        return gianhap;
    }

    public NhapProductEntity setGianhap(int gianhap) {
        this.gianhap = gianhap;
        return this;
    }

    @ManyToOne
    @JoinColumn(name = "phieunhap",referencedColumnName = "idnhaphang")
    @JsonBackReference
    public NhapHangEntity getNhapHangEntityByIdnhaphang() {
        return nhapHangEntityByIdnhaphang;
    }

    public NhapProductEntity setNhapHangEntityByIdnhaphang(NhapHangEntity nhapHangEntityByIdnhaphang) {
        this.nhapHangEntityByIdnhaphang = nhapHangEntityByIdnhaphang;
        return this;
    }

    @ManyToOne
    @JoinColumn(name = "productsizecolor" , referencedColumnName = "idproductsizecolor")
    public ProductSizeColorEntity getProductSizeColorEntity() {
        return productSizeColorEntity;
    }

    public NhapProductEntity setProductSizeColorEntity(ProductSizeColorEntity productSizeColorEntityBy) {
        this.productSizeColorEntity = productSizeColorEntityBy;
        return this;
    }
}
