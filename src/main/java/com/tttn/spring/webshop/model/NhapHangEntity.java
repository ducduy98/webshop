package com.tttn.spring.webshop.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

@Entity
@Table(name = "nhaphang")
public class NhapHangEntity {
    private String idnhaphang;


    private Timestamp ngaynhap;

    private String taikhoan;

    private Collection<NhapProductEntity> listNhapProductEntity;

    @Basic
    @Column(name = "idnhaphang")
    @Id
    public String getIdnhaphang() {
        return idnhaphang;
    }

    public NhapHangEntity setIdnhaphang(String idnhaphang) {
        this.idnhaphang = idnhaphang;
        return this;
    }


    @Column(name = "taikhoan")
    public String getTaikhoan() {
        return taikhoan;
    }

    public NhapHangEntity setTaikhoan(String taikhoan) {
        this.taikhoan = taikhoan;
        return this;
    }

    @Basic
    @Column(name = "ngaynhap")
    @DateTimeFormat(pattern = "dd-MM-YYYY hh:mm:ss")
    public Timestamp getNgaynhap() {
        return ngaynhap;
    }

    public NhapHangEntity setNgaynhap(Timestamp ngaynhap) {
        this.ngaynhap = ngaynhap;
        return this;
    }

    @OneToMany(mappedBy = "nhapHangEntityByIdnhaphang")
    public Collection<NhapProductEntity> getListNhapProductEntity() {
        return listNhapProductEntity;
    }


    public NhapHangEntity setListNhapProductEntity(Collection<NhapProductEntity> listNhapProductEntity) {
        this.listNhapProductEntity = listNhapProductEntity;
        return this;
    }
}
