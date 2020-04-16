package com.tttn.spring.webshop.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "sale", schema = "shop", catalog = "")
public class SaleEntity {
    private int idsale;
    private String noiDung;
    private Integer sale;
    private Integer status;
    private Timestamp dateOpen;
    private Timestamp dateClose;

    @JsonBackReference
    private Collection<ProductSaleEntity> productSalesByIdsale;

    @JsonBackReference
    private Collection<ProductEntity> productByIdProduct;

    @Id
    @Column(name = "idsale")
    public int getIdsale() {
        return idsale;
    }

    public void setIdsale(int idsale) {
        this.idsale = idsale;
    }

    @Basic
    @Column(name = "noidung")
    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    @Basic
    @Column(name = "sale")
    public Integer getSale() {
        return sale;
    }

    public void setSale(Integer sale) {
        this.sale = sale;
    }

    @Basic
    @Column(name = "status")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Basic
    @Column(name = "dateopen")
    public Timestamp getDateOpen() {
        return dateOpen;
    }

    public void setDateOpen(Timestamp dateOpen) {
        this.dateOpen = dateOpen;
    }

    @Basic
    @Column(name = "dateclose")
    public Timestamp getDateClose() {
        return dateClose;
    }

    public void setDateClose(Timestamp dateClose) {
        this.dateClose = dateClose;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SaleEntity that = (SaleEntity) o;
        return idsale == that.idsale &&
                Objects.equals(noiDung, that.noiDung) &&
                Objects.equals(sale, that.sale) &&
                Objects.equals(status, that.status) &&
                Objects.equals(dateOpen, that.dateOpen) &&
                Objects.equals(dateClose, that.dateClose);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idsale, noiDung, sale, status, dateOpen, dateClose);
    }

    @OneToMany(mappedBy = "saleBySale")
    public Collection<ProductSaleEntity> getProductSalesByIdsale() {
        return productSalesByIdsale;
    }

    @OneToMany(mappedBy = "saleBySale")
    public Collection<ProductEntity> getProductByIdProduct(){return productByIdProduct;}

    public void setProductSalesByIdsale(Collection<ProductSaleEntity> productSalesByIdsale) {
        this.productSalesByIdsale = productSalesByIdsale;
    }

    public void setProductByIdProduct(Collection<ProductEntity> productByIdProduct) {
        this.productByIdProduct = productByIdProduct;
    }
}
