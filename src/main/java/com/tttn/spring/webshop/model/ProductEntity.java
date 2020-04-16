package com.tttn.spring.webshop.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.prism.paint.Color;
import jdk.nashorn.internal.objects.NativeArray;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Entity
@Table(name = "product", schema = "shop", catalog = "")
public class ProductEntity {
    private int idproduct;
    private String product;
    private Integer sex;
    private Timestamp dateCreated;
    private Timestamp dateUpdate;
    private Integer number;
    private String image;
    private String note;
    private Integer sold;
    private Integer price;
    private String described;

    private Integer status;

    private Collection<CartEntity> cartsByIdproduct;

    private Collection<CommentEntity> commentsByIdproduct;

    private Collection<HinhanhEntity> hinhanhsByIdproduct;

    private Collection<ProductCategoryEntity> productCategoriesByIdproduct;

    private Collection<ProductColorEntity> productColorsByIdproduct;

    private Collection<ProductSaleEntity> productSalesByIdproduct;

    private Collection<ProductSizeEntity> productSizesByIdproduct;

    private Collection<ProductSizeColorEntity> productSizeColorEntities;


    private SaleEntity saleBySale;

    private String stringImage;


    @Id
    @Column(name = "idproduct")
    public int getIdproduct() {
        return idproduct;
    }

    public void setIdproduct(int idproduct) {
        this.idproduct = idproduct;
    }


    @Basic
    @Column(name = "product")
    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    @Basic
    @Column(name = "sex")
    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    @Basic
    @Column(name = "datecreated")
    public Timestamp getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Basic
    @Column(name = "dateupdate")
    public Timestamp getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(Timestamp dateUpdate) {
        this.dateUpdate = dateUpdate;
    }


    @Basic
    @Column(name = "image")
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Basic
    @Column(name = "note")
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Basic
    @Column(name = "sold")
    public Integer getSold() {
        return sold;
    }

    public void setSold(Integer sold) {
        this.sold = sold;
    }

    @Basic
    @Column(name = "price")
    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Basic
    @Column(name = "described")
    public String getDescribed() {
        return described;
    }

    public void setDescribed(String described) {
        this.described = described;
    }


    public ProductEntity setNumber(Integer number) {
        this.number = number;
        return this;
    }

    @Basic
    @Column(name ="status")
    public Integer getStatus() {
        return status;
    }

    public ProductEntity setStatus(Integer status) {
        this.status = status;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductEntity that = (ProductEntity) o;
        return idproduct == that.idproduct &&
                Objects.equals(product, that.product) &&
                Objects.equals(sex, that.sex) &&
                Objects.equals(dateCreated, that.dateCreated) &&
                Objects.equals(dateUpdate, that.dateUpdate) &&

                Objects.equals(image, that.image) &&
                Objects.equals(note, that.note) &&
                Objects.equals(sold, that.sold) &&
                Objects.equals(price, that.price) &&
                Objects.equals(described, that.described);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idproduct, product, sex, dateCreated, dateUpdate, image, note, sold, price, described);
    }

    @OneToMany(mappedBy = "productByProduct")
    @JsonBackReference
    public Collection<CartEntity> getCartsByIdproduct() {
        return cartsByIdproduct;
    }

    @OneToMany(mappedBy = "productByProduct")
    @JsonBackReference
    public Collection<ProductSizeColorEntity> getProductSizeColorEntities() {
        return productSizeColorEntities;
    }

    public ProductEntity setProductSizeColorEntities(Collection<ProductSizeColorEntity> productSizeColorEntities) {
        this.productSizeColorEntities = productSizeColorEntities;
        return this;
    }

    public void setCartsByIdproduct(Collection<CartEntity> cartsByIdproduct) {
        this.cartsByIdproduct = cartsByIdproduct;
    }

    @OneToMany(mappedBy = "productByProduct")
    @JsonBackReference
    public Collection<CommentEntity> getCommentsByIdproduct() {
        return commentsByIdproduct;
    }

    public void setCommentsByIdproduct(Collection<CommentEntity> commentsByIdproduct) {
        this.commentsByIdproduct = commentsByIdproduct;
    }

    @OneToMany(mappedBy = "productByProduct")
    public Collection<HinhanhEntity> getHinhanhsByIdproduct() {
        return hinhanhsByIdproduct;
    }

    public void setHinhanhsByIdproduct(Collection<HinhanhEntity> hinhanhsByIdproduct) {
        this.hinhanhsByIdproduct = hinhanhsByIdproduct;
    }

    @OneToMany(mappedBy = "productByProduct")
    public Collection<ProductCategoryEntity> getProductCategoriesByIdproduct() {
        return productCategoriesByIdproduct;
    }

    public void setProductCategoriesByIdproduct(Collection<ProductCategoryEntity> productCategoriesByIdproduct) {
        this.productCategoriesByIdproduct = productCategoriesByIdproduct;
    }

    @OneToMany(mappedBy = "productByProduct")
    @JsonBackReference
    public Collection<ProductColorEntity> getProductColorsByIdproduct() {
        return productColorsByIdproduct;
    }

    public void setProductColorsByIdproduct(Collection<ProductColorEntity> productColorsByIdproduct) {
        this.productColorsByIdproduct = productColorsByIdproduct;
    }

    @OneToMany(mappedBy = "productByProduct")
    @JsonBackReference
    public Collection<ProductSaleEntity> getProductSalesByIdproduct() {
        return productSalesByIdproduct;
    }

    public void setProductSalesByIdproduct(Collection<ProductSaleEntity> productSalesByIdproduct) {
        this.productSalesByIdproduct = productSalesByIdproduct;
    }

    @OneToMany(mappedBy = "productByProduct")
    @JsonBackReference
    public Collection<ProductSizeEntity> getProductSizesByIdproduct() {
        return productSizesByIdproduct;
    }

    public void setProductSizesByIdproduct(Collection<ProductSizeEntity> productSizesByIdproduct) {
        this.productSizesByIdproduct = productSizesByIdproduct;
    }

    @ManyToOne
    @JoinColumn(name = "sale", referencedColumnName = "idsale")
    @JsonBackReference
    public SaleEntity getSaleBySale() {
        return saleBySale;
    }

    public void setSaleBySale(SaleEntity saleBySale) {
        this.saleBySale = saleBySale;
    }

    public int giamGia() {
        Date date = Calendar.getInstance().getTime();
        Timestamp time = new Timestamp(date.getTime());
        if (getSaleBySale() != null && getSaleBySale().getStatus() == 1 && time.after(getSaleBySale().getDateOpen()) && time.before(getSaleBySale().getDateClose()))
            return getPrice() - (getPrice() * getSaleBySale().getSale() / 100);
        else return getPrice();
    }

    @Transient
    public int getNumber() {
        if (null == productSizeColorEntities) return 0;
        else return productSizeColorEntities.stream().mapToInt(i -> i.getNumber()).sum();
    }

    @Transient
    @JsonBackReference
    public Map<ColorEntity, List<SizeEntity>> getListMap() {
        Map<ColorEntity, List<SizeEntity>> mapOption = new HashMap<>();
        getProductSizeColorEntities().stream().forEach(c -> {

            if (!mapOption.containsKey(c.getColorByColor()) && c.getNumber() > 0) {
                List<SizeEntity> listSize = new ArrayList<>();
                listSize.add(c.getSizeBySize());
                mapOption.put(c.getColorByColor(), listSize);
            } else if(mapOption.containsKey(c.getColorByColor()) && c.getNumber()>0){
                mapOption.get(c.getColorByColor()).add(c.getSizeBySize());
            }

        });

        return mapOption;
    }

    @Transient
    public String formatCurrent() {
        Locale locale = new Locale("vi", "VN");
        Currency currency = Currency.getInstance("VND");


        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale);
        //numberFormat.setCurrency(currency);
        return numberFormat.format(giamGia());

    }

    @Transient
    public String getForMartDateCread() {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        if (null == dateCreated) {
            return "";
        } else {
            return simpleDateFormat.format(dateCreated);
        }

    }

    @Transient
    public String getForMartDateUpdate() {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if (null == dateUpdate) {
            return "";
        } else {
            return simpleDateFormat.format(dateUpdate);
        }


    }

    @Transient
    public int getGiamGia() {
        Date date = Calendar.getInstance().getTime();
        Timestamp time = new Timestamp(date.getTime());
        if (getSaleBySale() != null && getSaleBySale().getStatus() == 1 && time.after(getSaleBySale().getDateOpen()) && time.before(getSaleBySale().getDateClose()))
            return getPrice() - (getPrice() * getSaleBySale().getSale() / 100);
        else return getPrice();
    }

    @Transient
    @JsonIgnore
    public String getStringImage(){
        String giatri=getImage()+","+getHinhanhsByIdproduct().stream().map(c->c.getImage()).collect(Collectors.joining(","));
        System.out.println("get image "+giatri);
        if(null==giatri) return "khong co";
        return giatri;
    }

    public ProductEntity setStringImage(String stringImage) {
        this.stringImage = stringImage;
        return this;
    }
}
