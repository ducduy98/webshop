package com.tttn.spring.webshop.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.tttn.spring.webshop.unum.ColorEnum;
import com.tttn.spring.webshop.unum.EnumService;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "color", schema = "shop", catalog = "")
public class ColorEntity {
    private int idcolor;
    private String color;
    private Collection<CartEntity> cartsByIdcolor;

    @Id
    @Column(name = "idcolor")
    public int getIdcolor() {
        return idcolor;
    }

    public void setIdcolor(int idcolor) {
        this.idcolor = idcolor;
    }

    @Basic
    @Column(name = "color")
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ColorEntity that = (ColorEntity) o;
        return idcolor == that.idcolor &&
                Objects.equals(color, that.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idcolor, color);
    }

    @OneToMany(mappedBy = "colorByColor")
    @JsonBackReference
    public Collection<CartEntity> getCartsByIdcolor() {
        return cartsByIdcolor;
    }

    public void setCartsByIdcolor(Collection<CartEntity> cartsByIdcolor) {
        this.cartsByIdcolor = cartsByIdcolor;
    }



}
