package com.tttn.spring.webshop.service;


import com.tttn.spring.webshop.Crud.ColorCrud;
import com.tttn.spring.webshop.Crud.ProductCrud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchService {

    @Autowired
    ProductCrud productCrud;

    @Autowired
    ColorCrud colorCrud;

    public ServiceRestfull search(String name){
        ServiceRestfull serviceRestfull=new ServiceRestfull();
        serviceRestfull.setData(productCrud.findByProductContaining(name));

        return serviceRestfull;
    }

    public ServiceRestfull color(){
        ServiceRestfull serviceRestfull=new ServiceRestfull();
        serviceRestfull.setData(colorCrud.findAll());

        return serviceRestfull;
    }
}
