package com.tttn.spring.webshop.service;


import com.tttn.spring.webshop.Crud.ProductSizeColorCrud;
import com.tttn.spring.webshop.model.ProductSizeColorEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductSizeColorService {

    @Autowired
    ProductSizeColorCrud productSizeColorCrud;

    public ServiceRestfull getList(){
        ServiceRestfull cus=new ServiceRestfull();
        cus.setData(productSizeColorCrud.findAll());
        return cus;

    }

    public ServiceRestfull update(int soluong,int id){
        ProductSizeColorEntity productSizeColorEntity=productSizeColorCrud.findById(id).orElse(null);
        productSizeColorEntity.setNumber(soluong);
        productSizeColorCrud.save(productSizeColorEntity);
        ServiceRestfull serviceRestfull=getList();
        return serviceRestfull;


    }

    public ServiceRestfull search(int id,int size, int color){
        ServiceRestfull cus=new ServiceRestfull();
        System.out.println(" ten san pham la :"+productSizeColorCrud.getChiTiet(id, size, color).getProductByProduct().getProduct());
        cus.setData(productSizeColorCrud.getChiTiet(id, size, color));
        return cus;

    }
}
