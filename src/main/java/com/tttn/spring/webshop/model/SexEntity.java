package com.tttn.spring.webshop.model;

import com.tttn.spring.webshop.unum.EnumService;
import com.tttn.spring.webshop.unum.SexEnum;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "sex", schema = "shop", catalog = "")
public class SexEntity {
    private int idsex;

    @Basic
    @Column(name = "sex")
    private String sex;

    private Collection<CategoryEntity> categoriesByIdsex;

    //public SexEnum tenRieng(){
      //  return new EnumService().getSex(this.sex);
  //  }





    @Id
    @Column(name = "idsex")
    public int getIdsex() {
        return idsex;
    }

    public void setIdsex(int idsex) {
        this.idsex = idsex;
    }


    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SexEntity sexEntity = (SexEntity) o;
        return idsex == sexEntity.idsex &&
                Objects.equals(sex, sexEntity.sex);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idsex);
    }

    @OneToMany(mappedBy = "sexBySex")
    public Collection<CategoryEntity> getCategoriesByIdsex() {
        return categoriesByIdsex;
    }

    public void setCategoriesByIdsex(Collection<CategoryEntity> categoriesByIdsex) {
        this.categoriesByIdsex = categoriesByIdsex;
    }
}
