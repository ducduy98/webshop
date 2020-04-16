package com.tttn.spring.webshop.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "chitiet", schema = "shop", catalog = "")
public class ChitietEntity {
    private int iddiachi;
    private String ten;
    private String sdt;
    private String chitiet;
    private String thanhpho;
    private String huyen;
    private String xa;

    private int phiShip;

    private TaikhoanEntity taikhoanByTaikhoan;


    @Id
    @Column(name = "iddiachi")
    public int getIddiaChi() {
        return iddiachi;
    }



    @Basic
    @Column(name = "ten")
    public String getTen() {
        return ten;
    }






    @Basic
    @Column(name = "thanhpho")
    public String getThanhpho() {
        return thanhpho;
    }


    @Basic
    @Column(name = "huyen")
    public String getHuyen() {
        return huyen;
    }



    @Basic
    @Column(name = "xa")
    public String getXa() {
        return xa;
    }



    @Basic
    @Column(name = "chitiet")
    public String getChitiet() {
        return chitiet;
    }



    @Basic
    @Column(name = "sdt")
    public String getSdt() {
        return sdt;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChitietEntity that = (ChitietEntity) o;
        return iddiachi == that.iddiachi &&
                Objects.equals(ten, that.ten) &&

                Objects.equals(thanhpho, that.thanhpho) &&
                Objects.equals(huyen, that.huyen) &&
                Objects.equals(xa, that.xa) &&
                Objects.equals(chitiet, that.chitiet) &&
                Objects.equals(sdt, that.sdt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(iddiachi, ten, thanhpho, huyen, xa, chitiet, sdt);
    }

    @ManyToOne
    @JoinColumn(name = "taikhoan", referencedColumnName = "id")
    @JsonBackReference
    public TaikhoanEntity getTaikhoanByTaikhoan() {
        return taikhoanByTaikhoan;
    }

    public void setTaikhoanByTaikhoan(TaikhoanEntity taikhoanByTaikhoan) {
        this.taikhoanByTaikhoan = taikhoanByTaikhoan;
    }

    @Transient
    public String diaChi() {
        return this.chitiet+" - "+this.xa+" - "+this.huyen+" - "+this.thanhpho;
    }

    @Transient
    public String tenTuoi(){
        return this.ten+" - "+this.sdt;
    }

    @Transient
    public int getPhiShip() {
        if("THÀNH PHỐ HÀ NỘI".equalsIgnoreCase(getThanhpho())){
            phiShip=20000;
        }else{
            phiShip=34000;
        }
        return phiShip;
    }

    public ChitietEntity setPhiShip(int phiShip) {
        this.phiShip = phiShip;
        return this;
    }

    public ChitietEntity setIddiaChi(int iddiaChi) {
        this.iddiachi = iddiaChi;
        return this;
    }

    public ChitietEntity setTen(String ten) {
        this.ten = ten;
        return this;
    }

    public ChitietEntity setSdt(String sdt) {
        this.sdt = sdt;
        return this;
    }

    public ChitietEntity setChitiet(String chitiet) {
        this.chitiet = chitiet;
        return this;
    }

    public ChitietEntity setThanhpho(String thanhpho) {
        this.thanhpho = thanhpho;
        return this;
    }

    public ChitietEntity setHuyen(String huyen) {
        this.huyen = huyen;
        return this;
    }

    public ChitietEntity setXa(String xa) {
        this.xa = xa;
        return this;
    }

    @Transient
    public String getThongTin(){
        return String.format("%s - %s - %s - %s",getChitiet(),getXa(),getHuyen(),getThanhpho());
    }

    @Transient
    public String getKhach(){
        return String.format("%s - %s",getTen(),getSdt());
    }
}
