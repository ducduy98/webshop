package com.tttn.spring.webshop.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "product_sale", schema = "shop", catalog = "")
public class ProductSaleEntity {
    private int idproductSale;
    private Integer status;

    @JsonBackReference
    private ProductEntity productByProduct;
    private SaleEntity saleBySale;

    @Id
    @Column(name = "idproduct_sale")
    public int getIdproductSale() {
        return idproductSale;
    }

    public void setIdproductSale(int idproductSale) {
        this.idproductSale = idproductSale;
    }

    @Basic
    @Column(name = "status")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductSaleEntity that = (ProductSaleEntity) o;
        return idproductSale == that.idproductSale &&
                Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idproductSale, status);
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
    @JoinColumn(name = "sale", referencedColumnName = "idsale")
    public SaleEntity getSaleBySale() {
        return saleBySale;
    }

    public void setSaleBySale(SaleEntity saleBySale) {
        this.saleBySale = saleBySale;
    }
}
