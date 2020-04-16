package com.tttn.spring.webshop.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.apache.commons.lang3.time.DateFormatUtils;

import javax.persistence.*;
import java.sql.Timestamp;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(name = "phieumua", schema = "shop", catalog = "")
public class PhieumuaEntity {
    private String maPhieu;
    private Timestamp date;
    private Integer status;
    private CodeEntity codeByCode;
    private Integer ship;

    private ChitietEntity chitietEntity;
    private TaikhoanEntity taikhoanByTaikhoan;
    private Collection<PhieumuaCartEntity> phieumuaCartsByMaPhieu;

    @Id
    @Column(name = "maphieu")
    public String getMaPhieu() {
        return maPhieu;
    }




    @Basic
    @Column(name = "date")
    public Timestamp getDate() {
        return date;
    }



    @Basic
    @Column(name = "status")
    public Integer getStatus() {
        return status;
    }







    @Basic
    @Column(name = "ship")
    public Integer getShip() {
        return ship;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhieumuaEntity that = (PhieumuaEntity) o;
        return Objects.equals(maPhieu, that.maPhieu) &&
                Objects.equals(date, that.date) &&
                Objects.equals(status, that.status) &&
                Objects.equals(ship, that.ship);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maPhieu, date, status, ship);
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

    @ManyToOne
    @JoinColumn(name = "diachi",referencedColumnName = "iddiachi")
    public ChitietEntity getChitietEntity(){return chitietEntity;}

    public PhieumuaEntity setChitietEntity(ChitietEntity chitietEntity) {
        this.chitietEntity = chitietEntity;
        return this;
    }

    @ManyToOne
    @JoinColumn(name = "code" ,referencedColumnName = "idcode")
    public CodeEntity getCodeByCode(){return codeByCode;}

    public PhieumuaEntity setCodeByCode(CodeEntity codeByCode) {
        this.codeByCode = codeByCode;
        return this;
    }

    @OneToMany(mappedBy = "phieumuaByPhieumua")
    public Collection<PhieumuaCartEntity> getPhieumuaCartsByMaPhieu() {
        return phieumuaCartsByMaPhieu;
    }

    public PhieumuaEntity setMaPhieu(String maPhieu) {
        this.maPhieu = maPhieu;
        return this;
    }

    public PhieumuaEntity setDate(Timestamp date) {
        this.date = date;
        return this;
    }

    public PhieumuaEntity setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public PhieumuaEntity setShip(Integer ship) {
        this.ship = ship;
        return this;
    }

    public PhieumuaEntity setPhieumuaCartsByMaPhieu(Collection<PhieumuaCartEntity> phieumuaCartsByMaPhieu) {
        this.phieumuaCartsByMaPhieu = phieumuaCartsByMaPhieu;
        return this;
    }

    @Transient
    public Integer tienTongSale(){
        List<PhieumuaCartEntity> phieumuaCartEntities= (List<PhieumuaCartEntity>) getPhieumuaCartsByMaPhieu();
        int tongTien=phieumuaCartEntities.stream().mapToInt(pm->pm.getCartByCart().getProductSizeColorEntity().getProductByProduct().giamGia()).sum();
        return tongTien-getCodeByCode().getGiamGia();
    }

    @Transient
    public int thanhTien(){
       int tongtien= phieumuaCartsByMaPhieu.stream()
               .map(c->c.getCartByCart())
               .mapToInt(cart->cart.getThanhtien()*cart.getSoluong())
               .sum();
       if(null==getCodeByCode()){
           return tongtien+getChitietEntity().getPhiShip();
       }
       return tongtien+getChitietEntity().getPhiShip()-getCodeByCode().getGiamGia();
    }

    @Transient
    public String getThanhToan() {
        Locale locale = new Locale("vi", "VN");
        Currency currency = Currency.getInstance("VND");


        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale);
        //numberFormat.setCurrency(currency);
        return numberFormat.format(thanhTien());

    }

    @Transient
    public String getDateFormat(){

        SimpleDateFormat dateFormatUtils=new SimpleDateFormat("dd-MM-YYYY hh:mm:ss");
        return dateFormatUtils.format(getDate());
    }
}
