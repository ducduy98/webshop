package com.tttn.spring.webshop.service;


import com.tttn.spring.webshop.Crud.CodeCrud;
import com.tttn.spring.webshop.domain.CodeDomain;
import com.tttn.spring.webshop.model.CodeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class CodeService {

    @Autowired
    CodeCrud codeCrud;


    public int timId(){
        int id=0;
        Random random=new Random();
        while(id==0){
            id=random.nextInt(1_000_000_000)+1;
            if(null!=codeCrud.findById(id).orElse(null)){
                id=0;
            }
        }
        return id;
    }
    public ServiceRestfull getlist(){
        ServiceRestfull serviceRestfull=new ServiceRestfull();
        serviceRestfull.setData(codeCrud.findStatus1());
        return serviceRestfull;
    }

    public ServiceRestfull save(CodeDomain codeDomain){
        CodeEntity codeEntity=new CodeEntity();
        codeEntity.setIdcode(timId());
        codeEntity.setCode(codeDomain.getMacode());
        codeEntity.setChitiet(codeDomain.getChitiet());
        codeEntity.setGiamGia(codeDomain.getGiamgia());
        codeEntity.setPriceMin(codeDomain.getPrincemin());
        codeEntity.setStatus(1);
        codeCrud.save(codeEntity);
        ServiceRestfull serviceRestfull=new ServiceRestfull();
        serviceRestfull=getlist();
        return serviceRestfull;
    }

    public ServiceRestfull deleteTam(int id){
        CodeEntity codeEntity=codeCrud.findById(id).orElse(null);

        codeEntity.setStatus(2);
        codeCrud.save(codeEntity);
        ServiceRestfull serviceRestfull=new ServiceRestfull();
        serviceRestfull=getlist();
        return serviceRestfull;
    }

    public ServiceRestfull getListSatatus2(){
        ServiceRestfull serviceRestfull=new ServiceRestfull();
        serviceRestfull.setData(codeCrud.findStatus2());
        return serviceRestfull;
    }
}
