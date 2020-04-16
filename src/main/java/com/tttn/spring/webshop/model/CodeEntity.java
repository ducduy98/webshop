package com.tttn.spring.webshop.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "code", schema = "shop", catalog = "")
public class CodeEntity {
    private int idcode;
    private String code;
    private String chitiet;
    private Integer priceMin;
    private Integer status;
    private Integer giamGia;

    @Id
    @Column(name = "idcode")
    public int getIdcode() {
        return idcode;
    }

    public void setIdcode(int idcode) {
        this.idcode = idcode;
    }

    @Basic
    @Column(name = "code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Basic
    @Column(name="giamgia")
    public Integer getGiamGia(){return giamGia;}

    public CodeEntity setGiamGia(Integer giamGia) {
        this.giamGia = giamGia;
        return this;
    }

    @Basic
    @Column(name = "chitiet")
    public String getChitiet() {
        return chitiet;
    }

    public void setChitiet(String chitiet) {
        this.chitiet = chitiet;
    }

    @Basic
    @Column(name = "pricemin")
    public Integer getPriceMin() {
        return priceMin;
    }

    public void setPriceMin(Integer priceMin) {
        this.priceMin = priceMin;
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
        CodeEntity that = (CodeEntity) o;
        return idcode == that.idcode &&
                Objects.equals(code, that.code) &&
                Objects.equals(chitiet, that.chitiet) &&
                Objects.equals(priceMin, that.priceMin) &&
                Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idcode, code, chitiet, priceMin, status);
    }
}
