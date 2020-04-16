package com.tttn.spring.webshop.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "category", schema = "shop", catalog = "")
public class CategoryEntity {
    private int idcategory;
    private String category;
    private Timestamp dateCreated;
    private Timestamp dateUpdate;
    private SexEntity sexBySex;


    private Collection<ProductCategoryEntity> productCategoriesByIdcategory;

    @Id
    @Column(name = "idcategory")
    public int getIdcategory() {
        return idcategory;
    }

    public void setIdcategory(int idcategory) {
        this.idcategory = idcategory;
    }

    @Basic
    @Column(name = "category")
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Basic
    @Column(name = "dateCreated")
    public Timestamp getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Basic
    @Column(name = "dateUpdate")
    public Timestamp getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(Timestamp dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryEntity that = (CategoryEntity) o;
        return idcategory == that.idcategory &&
                Objects.equals(category, that.category) &&
                Objects.equals(dateCreated, that.dateCreated) &&
                Objects.equals(dateUpdate, that.dateUpdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idcategory, category, dateCreated, dateUpdate);
    }

    @ManyToOne
    @JoinColumn(name = "sex", referencedColumnName = "idsex")
    @JsonBackReference
    public SexEntity getSexBySex() {
        return sexBySex;
    }

    public void setSexBySex(SexEntity sexBySex) {
        this.sexBySex = sexBySex;
    }

    @OneToMany(mappedBy = "categoryByCategory")
    @JsonBackReference
    public Collection<ProductCategoryEntity> getProductCategoriesByIdcategory() {
        return productCategoriesByIdcategory;
    }

    public void setProductCategoriesByIdcategory(Collection<ProductCategoryEntity> productCategoriesByIdcategory) {
        this.productCategoriesByIdcategory = productCategoriesByIdcategory;
    }
}
