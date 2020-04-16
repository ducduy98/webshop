package com.tttn.spring.webshop.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "product_size_color", schema = "shop", catalog = "")
public class ProductSizeColorEntity {
    private int idproductSizeColor;
    private int number;


    private ProductEntity productByProduct;
    private SizeEntity sizeBySize;
    private ColorEntity colorByColor;
    private Collection<CartEntity> listCartEntity;

    private Collection<NhapProductEntity> listNhapHang;

    @Id
    @Column(name = "idproductsizecolor")
    public int getIdproductSizeColor() {
        return idproductSizeColor;
    }


    @Basic
    @Column(name = "number")
    public int getNumber(){return  number;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductSizeColorEntity that = (ProductSizeColorEntity) o;
        return idproductSizeColor == that.idproductSizeColor;

    }

    @Override
    public int hashCode() {
        return Objects.hash(idproductSizeColor);
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

    @OneToMany(mappedBy = "productSizeColorEntity")
    @JsonBackReference
    public Collection<CartEntity> getListCartEntity(){return listCartEntity;}

    public ProductSizeColorEntity setListCartEntity(Collection<CartEntity> listCartEntity) {
        this.listCartEntity = listCartEntity;
        return this;
    }

    public ProductSizeColorEntity setIdproductSizeColor(int idproductSizeColor) {
        this.idproductSizeColor = idproductSizeColor;
        return this;
    }

    public ProductSizeColorEntity setNumber(int number) {
        this.number = number;
        return this;
    }

    public ProductSizeColorEntity setProductByProduct(ProductEntity productByProduct) {
        this.productByProduct = productByProduct;
        return this;
    }

    public ProductSizeColorEntity setSizeBySize(SizeEntity sizeBySize) {
        this.sizeBySize = sizeBySize;
        return this;
    }

    public ProductSizeColorEntity setColorByColor(ColorEntity colorByColor) {
        this.colorByColor = colorByColor;
        return this;
    }

    @Transient
    public String getSanPham(){
        return String.format("%s - %s - %s"
                ,getProductByProduct().getProduct()
                ,getSizeBySize().getSize()
                ,getColorByColor().getColor());
    }

    @Transient
    public String getAnh(){
        return getProductByProduct().getImage();
    }

    @OneToMany(mappedBy = "productSizeColorEntity")
    @JsonBackReference
    public Collection<NhapProductEntity> getListNhapHang() {
        return listNhapHang;
    }

    public ProductSizeColorEntity setListNhapHang(Collection<NhapProductEntity> listNhapHang) {
        this.listNhapHang = listNhapHang;
        return this;
    }
}
