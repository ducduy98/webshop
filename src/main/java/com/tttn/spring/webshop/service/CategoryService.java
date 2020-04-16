package com.tttn.spring.webshop.service;


import com.tttn.spring.webshop.Crud.CategoryCrud;
import com.tttn.spring.webshop.model.CategoryEntity;
import com.tttn.spring.webshop.model.ProductEntity;
import com.tttn.spring.webshop.model.SexCategoryCustom;
import com.tttn.spring.webshop.model.SexEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service

public class CategoryService {

    @Autowired
    CategoryCrud categoryCrud;


    public ServiceRestfull IdFind(int id){
        ServiceRestfull serviceRestfull=new ServiceRestfull();
        serviceRestfull.setData(categoryCrud.findById(id).orElse(null));
        return serviceRestfull;
    }

    public int timId(){
        int id=0;
        Random random=new Random();
        while(id==0){
            id=random.nextInt(1_000_000_000)+1;
            if(null!=categoryCrud.findById(id).orElse(null)){
                id=0;
            }

        }

        return id;
    }


    public void save(SexCategoryCustom sexcate,SexEntity sex){
        ServiceRestfull serviceRestfull=new ServiceRestfull();
        CategoryEntity cate=new CategoryEntity();
        System.out.println("ten danh muc la :"+sexcate.getCategory());
        cate.setCategory(sexcate.getCategory());
        cate.setIdcategory(timId());
        cate.setSexBySex(sex);
        categoryCrud.save(cate);


    }
    public void update(SexCategoryCustom sexcate){

        CategoryEntity cate=categoryCrud.findById(sexcate.getIdCategory()).orElse(null);
        if(null!=cate){
            cate.setCategory(sexcate.getCategory());
            categoryCrud.save(cate);

        }
        else{

        }



    }

    public ServiceRestfull showMore(int id,int status){
        ServiceRestfull serviceRestfull=new ServiceRestfull();
        CategoryEntity cate=categoryCrud.findById(id).orElse(null);
       List<ProductEntity> listP=cate.getProductCategoriesByIdcategory().stream().map(pc->pc.getProductByProduct()).collect(Collectors.toList());
        if(1==status){
            serviceRestfull.setData(listP);
        }
        else if(2==status){
            listP.sort(new Comparator<ProductEntity>() {
                @Override
                public int compare(ProductEntity o1, ProductEntity o2) {
                    return o1.giamGia()-o2.getGiamGia();
                }
            });
            serviceRestfull.setData(listP);
        }else if(3==status){
            listP.sort(new Comparator<ProductEntity>() {
                @Override
                public int compare(ProductEntity o1, ProductEntity o2) {
                    return o2.getSold()-o1.getSold();
                }
            });
            serviceRestfull.setData(listP);
        }else if(4==status){
            listP.sort(new Comparator<ProductEntity>() {
                @Override
                public int compare(ProductEntity o1, ProductEntity o2) {
                    return o2.getDateCreated().compareTo(o1.getDateCreated());
                }
            });
            serviceRestfull.setData(listP);
        }

        return serviceRestfull;
    }
}
