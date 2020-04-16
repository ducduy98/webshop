package com.tttn.spring.webshop.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "phieumua_cart", schema = "shop", catalog = "")
public class PhieumuaCartEntity {
    private int idphieumuaCart;
    private PhieumuaEntity phieumuaByPhieumua;
    private CartEntity cartByCart;

    @Id
    @Column(name = "idphieumua_cart")
    public int getIdphieumuaCart() {
        return idphieumuaCart;
    }

    public void setIdphieumuaCart(int idphieumuaCart) {
        this.idphieumuaCart = idphieumuaCart;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhieumuaCartEntity that = (PhieumuaCartEntity) o;
        return idphieumuaCart == that.idphieumuaCart;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idphieumuaCart);
    }

    @ManyToOne
    @JoinColumn(name = "phieumua", referencedColumnName = "maPhieu")
    @JsonBackReference
    public PhieumuaEntity getPhieumuaByPhieumua() {
        return phieumuaByPhieumua;
    }

    public void setPhieumuaByPhieumua(PhieumuaEntity phieumuaByPhieumua) {
        this.phieumuaByPhieumua = phieumuaByPhieumua;
    }

    @ManyToOne
    @JoinColumn(name = "cart", referencedColumnName = "idcart")
    public CartEntity getCartByCart() {
        return cartByCart;
    }

    public void setCartByCart(CartEntity cartByCart) {
        this.cartByCart = cartByCart;
    }
}
