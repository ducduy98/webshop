package com.tttn.spring.webshop.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "size", schema = "shop", catalog = "")
public class SizeEntity {
    private int idsize;
    private Integer size;
    private Collection<CartEntity> cartsByIdsize;
    private Collection<ProductSizeEntity> productSizesByIdsize;

    @Id
    @Column(name = "idsize")
    public int getIdsize() {
        return idsize;
    }

    public void setIdsize(int idsize) {
        this.idsize = idsize;
    }

    @Basic
    @Column(name = "size")
    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SizeEntity that = (SizeEntity) o;
        return idsize == that.idsize &&
                Objects.equals(size, that.size);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idsize, size);
    }

    @OneToMany(mappedBy = "sizeBySize")
    @JsonBackReference
    public Collection<CartEntity> getCartsByIdsize() {
        return cartsByIdsize;
    }

    public void setCartsByIdsize(Collection<CartEntity> cartsByIdsize) {
        this.cartsByIdsize = cartsByIdsize;
    }

    @OneToMany(mappedBy = "sizeBySize")
    @JsonBackReference
    public Collection<ProductSizeEntity> getProductSizesByIdsize() {
        return productSizesByIdsize;
    }

    public void setProductSizesByIdsize(Collection<ProductSizeEntity> productSizesByIdsize) {
        this.productSizesByIdsize = productSizesByIdsize;
    }
}
