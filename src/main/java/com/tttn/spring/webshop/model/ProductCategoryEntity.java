package com.tttn.spring.webshop.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "product_category", schema = "shop", catalog = "")
public class ProductCategoryEntity {
    private int idproductCategory;


    private ProductEntity productByProduct;


    private CategoryEntity categoryByCategory;

    @Id
    @Column(name = "idproduct_category")
    public int getIdproductCategory() {
        return idproductCategory;
    }

    public void setIdproductCategory(int idproductCategory) {
        this.idproductCategory = idproductCategory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductCategoryEntity that = (ProductCategoryEntity) o;
        return idproductCategory == that.idproductCategory;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idproductCategory);
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

    @ManyToOne
    @JoinColumn(name = "category", referencedColumnName = "idcategory")
    public CategoryEntity getCategoryByCategory() {
        return categoryByCategory;
    }

    public void setCategoryByCategory(CategoryEntity categoryByCategory) {
        this.categoryByCategory = categoryByCategory;
    }
}
