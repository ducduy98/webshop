package com.tttn.spring.webshop.service;


import com.tttn.spring.webshop.Crud.CommentCrud;
import com.tttn.spring.webshop.Crud.ProductCrud;
import com.tttn.spring.webshop.Crud.TaikhoanCrud;
import com.tttn.spring.webshop.domain.CommentCustom;
import com.tttn.spring.webshop.model.CommentEntity;
import com.tttn.spring.webshop.model.TaikhoanEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    CommentCrud commentCrud;

    @Autowired
    TaikhoanCrud taikhoanCrud;

    @Autowired
    ProductCrud productCrud;

    public ServiceRestfull getlist(int product){
        ServiceRestfull serviceRestfull=new ServiceRestfull();
        List<CommentEntity> commentEntities=commentCrud.findProduct(product);
        serviceRestfull.setData(commentEntities);
        serviceRestfull.setMessage(String.valueOf(commentEntities.size()));
        return serviceRestfull;
    }

    public ServiceRestfull save(CommentCustom comment){
        ServiceRestfull serviceRestfull=new ServiceRestfull();
        Object principal= SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String id=null;
        if(principal instanceof UserDetails){
            id=((UserDetails) principal).getUsername();
        }
        if(id!=null){
            TaikhoanEntity taikhoanEntity=taikhoanCrud.findById(id).orElse(null);
            CommentEntity commentEntity=new CommentEntity();
            commentEntity.setNoidung(comment.getComment());
            commentEntity.setProductByProduct(productCrud.findById(comment.getIdproduct()).orElse(null));
            commentEntity.setTaikhoanByTaikhoan(taikhoanEntity);
            commentCrud.save(commentEntity);
        }
        serviceRestfull=getlist(comment.getIdproduct());
        return serviceRestfull;


    }
}
