package com.tttn.spring.webshop.service;

import com.tttn.spring.webshop.Crud.CategoryCrud;
import com.tttn.spring.webshop.Crud.HinhAnhCrud;
import com.tttn.spring.webshop.Crud.ProductCategoryCrud;
import com.tttn.spring.webshop.Crud.ProductCrud;
import com.tttn.spring.webshop.domain.Search;
import com.tttn.spring.webshop.exception.SaveExceptionJPA;
import com.tttn.spring.webshop.model.HinhanhEntity;
import com.tttn.spring.webshop.model.ProductCategoryEntity;
import com.tttn.spring.webshop.model.ProductCustom;
import com.tttn.spring.webshop.model.ProductEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.*;

@Service
public class ProductService {

    @Autowired
    ProductCrud productCrud;

    @Autowired
    HinhAnhCrud hinhAnhCrud;

    @Autowired
    ProductCategoryCrud productCategoryCrud;

    @Autowired
    CategoryCrud categoryCrud;


    public ServiceRestfull getList(){
        ServiceRestfull serviceRestfull=new ServiceRestfull();
        serviceRestfull.setData(productCrud.dateCreatedGetList());
        return serviceRestfull;
    }

    public List<ProductEntity>  list(){
        return (List<ProductEntity>) productCrud.findAll();
    }
    public ServiceRestfull getId(int id){
        ServiceRestfull serviceRestfull=new ServiceRestfull();
        serviceRestfull.setData(productCrud.findById(id));
        return serviceRestfull;
    }

    public ServiceRestfull save(ProductCustom productc){
        System.out.println("listname product "+productc.getImage());
        ServiceRestfull serviceRestfull=new ServiceRestfull();
        if(null==productc.getImage()){
            serviceRestfull=getList();
            serviceRestfull.setMessage("phai them it nhat 1 hinh anh");

        }
        else{
            String[] imageList=productc.getImage().split(",");
            List<String> list= Arrays.asList(imageList);
            try {
                save(list,productc);
            } catch (SaveExceptionJPA saveExceptionJPA) {
                saveExceptionJPA.printStackTrace();
            }




        }
        serviceRestfull=getList();
        serviceRestfull.setMessage("them san pham thanh cong");
        return serviceRestfull;
    }

    public  ServiceRestfull update(ProductCustom productc){

        ServiceRestfull serviceRestfull=new ServiceRestfull();
        if(null==productc.getImage()){
            serviceRestfull=getList();
            serviceRestfull.setMessage("phai them it nhat 1 hinh anh");

        }
        else{
            String[] imageList=productc.getImage().split(",");
            List<String> list= Arrays.asList(imageList);
            try {
                update(list,productc);
            } catch (SaveExceptionJPA saveExceptionJPA) {
                saveExceptionJPA.printStackTrace();
            }



            serviceRestfull=getList();
            serviceRestfull.setMessage("update san pham thanh cong");
        }
        return serviceRestfull;
    }
    public int timId(){
        int id=0;
        Random random=new Random();
        while(id==0){
            id=random.nextInt(1_000_000_000)+1;
            if(null!=productCrud.findById(id).orElse(null)){
                id=0;
            }

        }

        return id;
    }
    public int timIdProductCategory(){
        int id=0;
        Random random=new Random();
        while(id==0){
            id=random.nextInt(1_000_000_000)+1;
            if(null!=productCategoryCrud.findById(id).orElse(null)){
                id=0;
            }

        }

        return id;
    }

    @Transactional(rollbackFor = SaveExceptionJPA.class)
    public void save(List<String> list,ProductCustom productc) throws SaveExceptionJPA {
        int idProduct=timId();
        int idImage=timIdHinhAnh();
       if(list.size()<2){
           int idsanpham=idProduct;
           Date date = Calendar.getInstance().getTime();
           Timestamp time = new Timestamp(date.getTime());
           ProductEntity product = new ProductEntity();

           product.setIdproduct(idsanpham);
           product.setImage(list.get(0));
           product.setDateCreated(time);
           product.setStatus(1);
           product.setProduct(productc.getProduct());
           //    System.out.println("gia tien la :"+productc.getPrince());
           product.setPrice(productc.getPrince());
           product.setDescribed(productc.getDesc());
           productCrud.save(product);
           ProductCategoryEntity pce=new ProductCategoryEntity();
           pce.setProductByProduct(productCrud.findById(idsanpham).orElse(null));
           pce.setCategoryByCategory(categoryCrud.findById(productc.getCategory()).orElse(null));
           productCategoryCrud.save(pce);

       }else{
           try {
               Date date = Calendar.getInstance().getTime();
               Timestamp time = new Timestamp(date.getTime());
               ProductEntity product = new ProductEntity();

               product.setIdproduct(idProduct);
               product.setImage(list.get(0));
               product.setDateCreated(time);
               product.setStatus(1);
               product.setProduct(productc.getProduct());
               //    System.out.println("gia tien la :"+productc.getPrince());
               product.setPrice(productc.getPrince());
               product.setDescribed(productc.getDesc());
               product.setSold(0);
               productCrud.save(product);

               ProductCategoryEntity pce=new ProductCategoryEntity();
               pce.setProductByProduct(productCrud.findById(idProduct).orElse(null));
               pce.setCategoryByCategory(categoryCrud.findById(productc.getCategory()).orElse(null));
               productCategoryCrud.save(pce);

               for (int i = 1; i < list.size(); i++) {
                   HinhanhEntity hinhanhEntity = new HinhanhEntity();
                   hinhanhEntity.setIdhinhanh(timIdHinhAnh());
                   hinhanhEntity.setImage(list.get(i));
                   hinhanhEntity.setProductByProduct(productCrud.findById(idProduct).orElse(null));
                   hinhAnhCrud.save(hinhanhEntity);

               }

           }catch (Exception e){
               throw new SaveExceptionJPA("roll back lai sql ");
           }
       }
    }

    public int timIdHinhAnh(){
        int id=0;
        Random random=new Random();
        while(id==0){
            id=random.nextInt(1_000_000_000)+1;
            if(null!=hinhAnhCrud.findById(id).orElse(null)){
                id=0;
            }

        }

        return id;
    }


    @Transactional(rollbackFor = SaveExceptionJPA.class)
    public void update(List<String> list,ProductCustom productc) throws SaveExceptionJPA {

        int idImage=timIdHinhAnh();
        System.out.println("so luong hinh anh :"+list.get(0).startsWith("product"));
        if(list.size()==1 && !list.get(0).startsWith("product")){
            Date date = Calendar.getInstance().getTime();
            Timestamp time = new Timestamp(date.getTime());
            ProductEntity product = productCrud.findById(productc.getIdProduct()).orElse(null);

            // product.setIdproduct(idProduct);

            product.setDateUpdate(time);
            product.setProduct(productc.getProduct());
            //    System.out.println("gia tien la :"+productc.getPrince());
            product.setPrice(productc.getPrince());
            product.setDescribed(productc.getDesc());
            productCrud.save(product);
            product.getProductCategoriesByIdproduct().stream().forEach(c->productCategoryCrud.delete(c));

            ProductCategoryEntity pce=new ProductCategoryEntity();
            pce.setProductByProduct(productCrud.findById(product.getIdproduct()).orElse(null));
            pce.setCategoryByCategory(categoryCrud.findById(productc.getCategory()).orElse(null));
            productCategoryCrud.save(pce);
        }
        else if(list.get(0).startsWith("product")&&list.size()==1){
            Date date = Calendar.getInstance().getTime();
            Timestamp time = new Timestamp(date.getTime());
            ProductEntity product = productCrud.findById(productc.getIdProduct()).orElse(null);

           // product.setIdproduct(idProduct);
            product.setImage(list.get(0));
            product.setDateUpdate(time);
            product.setProduct(productc.getProduct());
            //    System.out.println("gia tien la :"+productc.getPrince());
            product.setPrice(productc.getPrince());
            product.setDescribed(productc.getDesc());
            productCrud.save(product);
            product.getProductCategoriesByIdproduct().stream().forEach(c->productCategoryCrud.delete(c));

            ProductCategoryEntity pce=new ProductCategoryEntity();
            pce.setProductByProduct(productCrud.findById(product.getIdproduct()).orElse(null));
            pce.setCategoryByCategory(categoryCrud.findById(productc.getCategory()).orElse(null));
            productCategoryCrud.save(pce);

        }else{
            try {


                Date date = Calendar.getInstance().getTime();
                Timestamp time = new Timestamp(date.getTime());
                ProductEntity product = productCrud.findById(productc.getIdProduct()).orElse(null);


                product.setImage(list.get(0));
                product.setDateUpdate(time);
                product.setProduct(productc.getProduct());
                //    System.out.println("gia tien la :"+productc.getPrince());
                product.setPrice(productc.getPrince());
                product.setDescribed(productc.getDesc());
                productCrud.save(product);
                product.getProductCategoriesByIdproduct().stream().forEach(c->productCategoryCrud.delete(c));

                ProductCategoryEntity pce=new ProductCategoryEntity();
                pce.setProductByProduct(productCrud.findById(product.getIdproduct()).orElse(null));
                pce.setCategoryByCategory(categoryCrud.findById(productc.getCategory()).orElse(null));
                productCategoryCrud.save(pce);

                List<HinhanhEntity> listHA= (List<HinhanhEntity>) product.getHinhanhsByIdproduct();

                // delete het hinh anh
                listHA.stream().forEach(ha->hinhAnhCrud.delete(ha));

                for (int i = 1; i < list.size(); i++) {
                    HinhanhEntity hinhanhEntity = new HinhanhEntity();
                    hinhanhEntity.setIdhinhanh(timIdHinhAnh());
                    hinhanhEntity.setImage(list.get(i));
                    hinhanhEntity.setProductByProduct(product);
                    hinhAnhCrud.save(hinhanhEntity);

                }

            }catch (Exception e){
                throw new SaveExceptionJPA("roll back lai sql ");
            }
        }
    }

    public ServiceRestfull timkiem(Search s){
        ServiceRestfull serviceRestfull=new ServiceRestfull();
        List<ProductEntity> listP=productCrud.findByProductContaining(s.getTukhoa());
        if(1==s.getStatus()){
            serviceRestfull.setData(listP);
        }
        else if(2==s.getStatus()){
            listP.sort(new Comparator<ProductEntity>() {
                @Override
                public int compare(ProductEntity o1, ProductEntity o2) {
                    return o1.giamGia()-o2.getGiamGia();
                }
            });
            serviceRestfull.setData(listP);
        }else if(3==s.getStatus()){
            listP.sort(new Comparator<ProductEntity>() {
                @Override
                public int compare(ProductEntity o1, ProductEntity o2) {
                    return o2.getSold()-o1.getSold();
                }
            });
            serviceRestfull.setData(listP);
        }else if(4==s.getStatus()){
            listP.sort(new Comparator<ProductEntity>() {
                @Override
                public int compare(ProductEntity o1, ProductEntity o2) {
                    return o2.getDateCreated().compareTo(o1.getDateCreated());
                }
            });

        }
        serviceRestfull.setData(listP);
        return serviceRestfull;
    }

    public ServiceRestfull delete(int idProduct){
        ServiceRestfull serviceRestfull=new ServiceRestfull();
        ProductEntity productEntity=productCrud.findById(idProduct).orElseThrow(null);
        productEntity.setStatus(2);
        productCrud.save(productEntity);
        serviceRestfull=getList();
        return serviceRestfull;
    }

}
