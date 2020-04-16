package com.tttn.spring.webshop.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "comment", schema = "shop", catalog = "")
public class CommentEntity {
    private int idcomment;
    private String image;
    private String noidung;
    private TaikhoanEntity taikhoanByTaikhoan;

    private ProductEntity productByProduct;

    @Id
    @Column(name = "idcomment")
    public int getIdcomment() {
        return idcomment;
    }

    public void setIdcomment(int idcomment) {
        this.idcomment = idcomment;
    }

    @Basic
    @Column(name = "image")
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Basic
    @Column(name = "noidung")
    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentEntity that = (CommentEntity) o;
        return idcomment == that.idcomment &&
                Objects.equals(image, that.image) &&
                Objects.equals(noidung, that.noidung);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idcomment, image, noidung);
    }

    @ManyToOne
    @JoinColumn(name = "taikhoan", referencedColumnName = "id")
    public TaikhoanEntity getTaikhoanByTaikhoan() {
        return taikhoanByTaikhoan;
    }

    public void setTaikhoanByTaikhoan(TaikhoanEntity taikhoanByTaikhoan) {
        this.taikhoanByTaikhoan = taikhoanByTaikhoan;
    }

    @ManyToOne
    @JoinColumn(name = "product", referencedColumnName = "idproduct")
    @JsonBackReference
    public ProductEntity getProductByProduct() {
        return productByProduct;
    }

    public void setProductByProduct(ProductEntity productByProduct) {
        this.productByProduct = productByProduct;
    }
}
