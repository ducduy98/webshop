package com.tttn.spring.webshop.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "product_color", schema = "shop", catalog = "")
public class ProductColorEntity {
    private int idproductColor;

    @JsonBackReference
    private ProductEntity productByProduct;
    private ColorEntity colorByColor;

    @Id
    @Column(name = "idproduct_color")
    public int getIdproductColor() {
        return idproductColor;
    }

    public void setIdproductColor(int idproductColor) {
        this.idproductColor = idproductColor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductColorEntity that = (ProductColorEntity) o;
        return idproductColor == that.idproductColor;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idproductColor);
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
    @JoinColumn(name = "color", referencedColumnName = "idcolor")
    public ColorEntity getColorByColor() {
        return colorByColor;
    }

    public void setColorByColor(ColorEntity colorByColor) {
        this.colorByColor = colorByColor;
    }
}
