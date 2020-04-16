package com.tttn.spring.webshop.service;

import com.tttn.spring.webshop.Crud.SexCrud;
import com.tttn.spring.webshop.model.CategoryEntity;
import com.tttn.spring.webshop.model.SexCategoryCustom;
import com.tttn.spring.webshop.model.SexEntity;
import com.tttn.spring.webshop.unum.SexEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class SexService {

    @Autowired
    SexCrud sexCrud;

    @Autowired
    CategoryService categoryService;

    public int timId(){
        int id=0;
        Random random=new Random();
        while(id==0){
            id=random.nextInt(1_000_000_000)+1;
            if(null!=sexCrud.findById(id).orElse(null)){
                id=0;
            }

        }

        return id;
    }


    public ServiceRestfull findId(int id){
        ServiceRestfull serviceRestfull=new ServiceRestfull();
        serviceRestfull.setData(sexCrud.findById(id).orElse(null));
        return serviceRestfull;
    }
    public ServiceRestfull getList(){
        ServiceRestfull serviceRestfull=new ServiceRestfull();
        serviceRestfull.setData(sexCrud.findAll());
        return serviceRestfull;
    }

    public ServiceRestfull save(SexCategoryCustom sexCate){
        ServiceRestfull serviceRestfull=new ServiceRestfull();
        SexEntity sex=new SexEntity();
        sex.setSex(sexCate.getSex());
        sex.setIdsex(timId());
        sexCrud.save(sex);
        serviceRestfull=getList();
        serviceRestfull.setMessage("them doi tuong thanh cong");
        return serviceRestfull;

    }
    public ServiceRestfull update(SexCategoryCustom sexCate){
        System.out.println("id sex :"+sexCate.getIdSex()+"----"+sexCate.getSex());
        ServiceRestfull serviceRestfull=new ServiceRestfull();
        SexEntity sex=sexCrud.findById(sexCate.getIdSex()).orElse(null);
        if(null!=sex){
            sex.setSex(sexCate.getSex());
            sexCrud.save(sex);
            serviceRestfull=getList();
            serviceRestfull.setMessage("update thanh cong doi tuong");
        }
        else{
            serviceRestfull.setMessage("khong the update");
        }

        return serviceRestfull;

    }

    public ServiceRestfull saveCategory(SexCategoryCustom sexCate){
        ServiceRestfull serviceRestfull=new ServiceRestfull();
        SexEntity sex=sexCrud.findById(sexCate.getIdSex()).orElse(null);

        categoryService.save(sexCate,sex);
        serviceRestfull=getList();
        return serviceRestfull;

    }

    public ServiceRestfull updateCategory(SexCategoryCustom sexCate){
        System.out.println("id sex :"+sexCate.getIdCategory()+"----"+sexCate.getCategory());
        ServiceRestfull serviceRestfull=new ServiceRestfull();
       // SexEntity sex=sexCrud.findById(sexCate.getIdSex()).orElse(null);

        categoryService.update(sexCate);

        serviceRestfull=getList();
        serviceRestfull.setMessage("update danh muc thanh cong");
        return serviceRestfull;

    }


}
