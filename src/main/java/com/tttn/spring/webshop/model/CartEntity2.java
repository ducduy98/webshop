package com.tttn.spring.webshop.model;

import java.sql.Timestamp;
import java.util.Collection;

public class CartEntity2 {

    private int idcart;
    private Timestamp dateCart;
    private Integer soluong;
    private TaikhoanEntity taikhoanByTaikhoan;
    private ProductEntity productByProduct;
    private SizeEntity sizeBySize;
    private ColorEntity colorByColor;
    //private Collection<PhieumuaCartEntity> phieumuaCartsByIdcart;
}
