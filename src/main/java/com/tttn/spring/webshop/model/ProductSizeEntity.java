package com.tttn.spring.webshop.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "product_size", schema = "shop", catalog = "")
public class ProductSizeEntity {
    private int idproductSize;

    @JsonBackReference
    private ProductEntity productByProduct;
    private SizeEntity sizeBySize;

    @Id
    @Column(name = "idproduct_size")
    public int getIdproductSize() {
        return idproductSize;
    }

    public void setIdproductSize(int idproductSize) {
        this.idproductSize = idproductSize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductSizeEntity that = (ProductSizeEntity) o;
        return idproductSize == that.idproductSize;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idproductSize);
    }

    @ManyToOne
    @JoinColumn(name = "product", referencedColumnName = "idproduct")
    public ProductEntity getProductByProduct() {
        return productByProduct;
    }

    public void setProductByProduct(ProductEntity productByProduct) {
        this.productByProduct = productByProduct;
    }

    @ManyToOne
    @JoinColumn(name = "size", referencedColumnName = "idsize")
    public SizeEntity getSizeBySize() {
        return sizeBySize;
    }

    public void setSizeBySize(SizeEntity sizeBySize) {
        this.sizeBySize = sizeBySize;
    }
}
