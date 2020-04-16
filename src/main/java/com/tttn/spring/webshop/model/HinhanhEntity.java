package com.tttn.spring.webshop.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "hinhanh", schema = "shop", catalog = "")
public class HinhanhEntity {
    private int idhinhanh;
    private String image;

    @JsonBackReference
    private ProductEntity productByProduct;

    @Id
    @Column(name = "idhinhanh")
    public int getIdhinhanh() {
        return idhinhanh;
    }

    public void setIdhinhanh(int idhinhanh) {
        this.idhinhanh = idhinhanh;
    }

    @Basic
    @Column(name = "image")
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HinhanhEntity that = (HinhanhEntity) o;
        return idhinhanh == that.idhinhanh &&
                Objects.equals(image, that.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idhinhanh, image);
    }

    @ManyToOne
    @JoinColumn(name = "product", referencedColumnName = "idproduct")
    public ProductEntity getProductByProduct() {
        return productByProduct;
    }

    public void setProductByProduct(ProductEntity productByProduct) {
        this.productByProduct = productByProduct;
    }
}
