package com.tttn.spring.webshop.service;


import com.tttn.spring.webshop.Crud.*;
import com.tttn.spring.webshop.domain.ProductSCCustom;
import com.tttn.spring.webshop.exception.SaveExceptionJPA;
import com.tttn.spring.webshop.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.Date;

@Service
public class PhieuNhapService {

    @Autowired
    NhapHangCrud nhapHangCrud;

    @Autowired
    ProductSizeColorCrud productSizeColorCrud;

    @Autowired
    NhapProductCrud nhapProductCrud;

    @Autowired
    SizeCrud sizeCrud;
    @Autowired
    ColorCrud colorCrud;

    @Autowired
    ProductCrud productCrud;

    @Transactional(rollbackFor = SaveExceptionJPA.class)
    public ServiceRestfull add(ProductSCCustom custom) throws SaveExceptionJPA {
        Object principal= SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String id=null;
        if(principal instanceof UserDetails){
            id=((UserDetails) principal).getUsername();
        }
        ServiceRestfull serviceRestfull = new ServiceRestfull();
        try {
            if (null == find(custom.getMaphieu())) {
                Date date = new Date();
                Timestamp time = new Timestamp(date.getTime());
                NhapHangEntity nhap = new NhapHangEntity();
                nhap.setIdnhaphang(custom.getMaphieu()).setNgaynhap(time).setTaikhoan(id);
                nhapHangCrud.save(nhap);
            }
            NhapHangEntity nhaphang = find(custom.getMaphieu());
            NhapProductEntity nhapProduct = new NhapProductEntity();
            nhapProduct.setNhapHangEntityByIdnhaphang(nhaphang);
            nhapProduct.setProductSizeColorEntity(getProductSizeColor1(custom.getProduct(), custom.getSize(), custom.getColor(), custom.getNumber()));
            nhapProduct.setSoluong(custom.getNumber());
            nhapProduct.setGianhap(custom.getGia());
            nhapProductCrud.save(nhapProduct);
            serviceRestfull.setData(nhapProductCrud.findNhap(custom.getMaphieu()));


        } catch (Exception ex) {
            throw new SaveExceptionJPA("xay ra loi gi do can roll back lai");
        }

        return serviceRestfull;
    }

    public NhapHangEntity find(String ma) {
        return nhapHangCrud.findById(ma).orElse(null);
    }

    public SizeEntity getSize(int size) {
        if (0 == sizeCrud.findBySize(size).size()) {
            SizeEntity sizeEntity = new SizeEntity();
            sizeEntity.setIdsize(size);
            sizeEntity.setSize(size);
            sizeCrud.save(sizeEntity);
            return sizeEntity;
        }
        return sizeCrud.findBySize(size).get(0);
    }

    public ColorEntity getColor(int color) {
        return colorCrud.findById(color).orElse(null);
    }

    public ProductEntity getProduct(int product) {
        return productCrud.findById(product).orElse(null);
    }

    public ProductSizeColorEntity getProductSizeColor1(int product, int size, int color, int number) {
        if (null == productSizeColorCrud.getChiTiet(product, getSize(size).getIdsize()
                , getColor(color).getIdcolor())) {
            ProductSizeColorEntity productSizeColorEntity = new ProductSizeColorEntity();
            productSizeColorEntity.setNumber(number).setColorByColor(getColor(color))
                    .setSizeBySize(getSize(size)).setProductByProduct(getProduct(product));
            productSizeColorCrud.save(productSizeColorEntity);
            return productSizeColorCrud.getChiTiet(product, getSize(size).getIdsize()
                    , getColor(color).getIdcolor());
        }
        ProductSizeColorEntity getra = productSizeColorCrud.getChiTiet(product, getSize(size).getIdsize()
                , getColor(color).getIdcolor());
        getra.setNumber(getra.getNumber() + number);
        productSizeColorCrud.save(getra);
        return getra;
    }

    @Transactional(rollbackFor = SaveExceptionJPA.class)
    public ServiceRestfull cancel(int manhap,String maphieu){
       try{
           NhapProductEntity nhap=nhapProductCrud.findById(manhap).orElse(null);
           ProductSizeColorEntity pro=nhap.getProductSizeColorEntity();
           pro.setNumber(pro.getNumber()-nhap.getSoluong());
           productSizeColorCrud.save(pro);
           nhapProductCrud.delete(nhap);
       }catch (Exception ec){
           try {
               throw new SaveExceptionJPA("bi loi trong qua trinh thuc hien");
           } catch (SaveExceptionJPA saveExceptionJPA) {
               saveExceptionJPA.printStackTrace();
           }
       }
        ServiceRestfull serviceRestfull=new ServiceRestfull();
        serviceRestfull.setData(nhapProductCrud.findNhap(maphieu));
        serviceRestfull.setMessage("xoa thanh cong");
        return serviceRestfull;
    }
}
