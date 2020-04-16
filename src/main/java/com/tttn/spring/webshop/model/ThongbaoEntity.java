package com.tttn.spring.webshop.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "thongbao", schema = "shop", catalog = "")
public class ThongbaoEntity {
    private int idthongbao;
    private String noidung;
    private String image;

    @Id
    @Column(name = "idthongbao")
    public int getIdthongbao() {
        return idthongbao;
    }

    public void setIdthongbao(int idthongbao) {
        this.idthongbao = idthongbao;
    }

    @Basic
    @Column(name = "noidung")
    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    @Basic
    @Column(name = "image")
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ThongbaoEntity that = (ThongbaoEntity) o;
        return idthongbao == that.idthongbao &&
                Objects.equals(noidung, that.noidung) &&
                Objects.equals(image, that.image);
    }


    @Override
    public int hashCode() {
        return Objects.hash(idthongbao, noidung, image);
    }
}
