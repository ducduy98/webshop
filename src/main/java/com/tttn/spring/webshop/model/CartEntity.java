package com.tttn.spring.webshop.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Entity
@Table(name = "cart", schema = "shop", catalog = "")
public class CartEntity {
    private int idcart;
    private Timestamp dateCart;
    private Integer soluong;
    private Integer status;
    private TaikhoanEntity taikhoanByTaikhoan;

    @JsonBackReference
    private ProductEntity productByProduct;
    private SizeEntity sizeBySize;
    private ColorEntity colorByColor;
    private Collection<PhieumuaCartEntity> phieumuaCartsByIdcart;
    private ProductSizeColorEntity productSizeColorEntity;

    private int thanhtien;


    @Id
    @Column(name = "idcart")
    public int getIdcart() {
        return idcart;
    }

    @Basic
    @Column(name = "thanhtien")
    public int getThanhtien() {
        return thanhtien;
    }

    public CartEntity setThanhtien(int thanhtien) {
        this.thanhtien = thanhtien;
        return this;
    }

    @Basic
    @Column(name = "dateCart")
    public Timestamp getDateCart() {
        return dateCart;
    }



    @Basic
    @Column(name = "soluong")
    public Integer getSoluong() {
        return soluong;
    }


    @Basic
    @Column(name = "status")
    public Integer getStatus(){return status;}

    public CartEntity setStatus(Integer status) {
        this.status = status;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartEntity that = (CartEntity) o;
        return idcart == that.idcart &&
                Objects.equals(dateCart, that.dateCart) &&
                Objects.equals(soluong, that.soluong);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idcart, dateCart, soluong);
    }

    @ManyToOne
    @JoinColumn(name = "taikhoan", referencedColumnName = "id")
    @JsonBackReference
    public TaikhoanEntity getTaikhoanByTaikhoan() {
        return taikhoanByTaikhoan;
    }



    @ManyToOne
    @JoinColumn(name = "product", referencedColumnName = "idproduct")
    @JsonBackReference
    public ProductEntity getProductByProduct() {
        return productByProduct;
    }


    @ManyToOne
    @JoinColumn(name = "size", referencedColumnName = "idsize")
    @JsonBackReference
    public SizeEntity getSizeBySize() {
        return sizeBySize;
    }


    @ManyToOne
    @JoinColumn(name = "color", referencedColumnName = "idcolor")
    @JsonBackReference
    public ColorEntity getColorByColor() {
        return colorByColor;
    }

    @ManyToOne
    @JoinColumn(name = "productSizeColor" ,referencedColumnName = "idproductSizeColor")
    public ProductSizeColorEntity getProductSizeColorEntity(){return productSizeColorEntity;}

    @OneToMany(mappedBy = "cartByCart")
    @JsonBackReference
    public Collection<PhieumuaCartEntity> getPhieumuaCartsByIdcart() {
        return phieumuaCartsByIdcart;
    }

    public CartEntity setProductSizeColorEntity(ProductSizeColorEntity productSizeColorEntity) {
        this.productSizeColorEntity = productSizeColorEntity;
        return this;
    }

    public CartEntity setIdcart(int idcart) {
        this.idcart = idcart;
        return this;
    }

    public CartEntity setDateCart(Timestamp dateCart) {
        this.dateCart = dateCart;
        return this;
    }

    public CartEntity setSoluong(Integer soluong) {
        this.soluong = soluong;
        return this;
    }

    public CartEntity setTaikhoanByTaikhoan(TaikhoanEntity taikhoanByTaikhoan) {
        this.taikhoanByTaikhoan = taikhoanByTaikhoan;
        return this;
    }

    public CartEntity setProductByProduct(ProductEntity productByProduct) {
        this.productByProduct = productByProduct;
        return this;
    }

    public CartEntity setSizeBySize(SizeEntity sizeBySize) {
        this.sizeBySize = sizeBySize;
        return this;
    }

    public CartEntity setColorByColor(ColorEntity colorByColor) {
        this.colorByColor = colorByColor;
        return this;
    }

    public CartEntity setPhieumuaCartsByIdcart(Collection<PhieumuaCartEntity> phieumuaCartsByIdcart) {
        this.phieumuaCartsByIdcart = phieumuaCartsByIdcart;
        return this;
    }

    public String forMartDate(){

        SimpleDateFormat simpleDateFormat =new SimpleDateFormat("dd-mm-yyyy");
        return simpleDateFormat.format(dateCart);

    }

    @Transient
    public double thanhTien(){
        return getSoluong()*getProductSizeColorEntity().getProductByProduct().giamGia();
    }

    public String forMatTienTe(){
        Locale locale = new Locale("vi", "VN");
        Currency currency = Currency.getInstance("VND");


        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale);
        //numberFormat.setCurrency(currency);
        System.out.println("tien"+ numberFormat.format(thanhTien()));
        return numberFormat.format(thanhTien());
    }
}
