package com.tttn.spring.webshop.service;

import com.tttn.spring.webshop.Crud.*;
import com.tttn.spring.webshop.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Random;

@Service
public class Restfull {

    @Autowired
    ProvinceCrud provinceCrud;

    @Autowired
    DistrictCrud districtCrud;

    @Autowired
    WardCrud wardCrud;

    @Autowired
    ChiTietCrud chiTietCrud;

    @Autowired
    TaikhoanCrud taikhoanCrud;

    public ServiceRestfull getThanhPho(){
        List<ProvinceEntity> provinceEntity= (List<ProvinceEntity>) provinceCrud.findAll();
        ServiceRestfull serviceRestfull=new ServiceRestfull();
        serviceRestfull.setData(provinceEntity).setMessage("get thanh pho");
        return serviceRestfull;
    }

    public ServiceRestfull getHuyen(String id){
        List<DistrictEntity> listD= (List<DistrictEntity>) districtCrud.getHuyen(id);
        ServiceRestfull serviceRestfull=new ServiceRestfull();
        serviceRestfull.setData(listD).setMessage("get huyen");
        return serviceRestfull;
    }

    public ServiceRestfull getXa(String id){
        List<WardEntity> listX= (List<WardEntity>) wardCrud.getXa(id);
        ServiceRestfull serviceRestfull=new ServiceRestfull();
        serviceRestfull.setData(listX).setMessage("get xa");
        return serviceRestfull;
    }

    public ServiceRestfull saveChiTiet(ChiTietJson chitietEntity){
        System.out.println("ho ten la :"+chitietEntity.getTen());
        ServiceRestfull serviceRestfull=new ServiceRestfull();
        Object principal= SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String id=null;
        if(principal instanceof UserDetails){
            id=((UserDetails) principal).getUsername();
        }
        if(id!=null){
            ChitietEntity chitiet=new ChitietEntity();

            int idChiTiet;
            int i = 1;
            Random random=new Random();
            do {
                idChiTiet = random.nextInt(1000000000) + 1;
                if (chiTietCrud.findById(idChiTiet).orElse(null) != null) {
                    i = 2;
                } else {
                    i = 1;
                }
            } while (i > 1);
            System.out.println("id chi tiet"+idChiTiet);
            chitiet.setIddiaChi(idChiTiet).setTen(chitietEntity.getTen()).setSdt(chitietEntity.getSdt())
                    .setThanhpho(chitietEntity.getThanhpho()).setHuyen(chitietEntity.getHuyen())
                    .setXa(chitietEntity.getXa()).setChitiet(chitietEntity.getChitiet())
                    .setTaikhoanByTaikhoan(taikhoanCrud.findById(id).orElse(null));
            chiTietCrud.save(chitiet);

            serviceRestfull.setData(chitiet);

        }
        else if(id==null){
            ChitietEntity chitiet=new ChitietEntity();

            int idChiTiet;
            int i = 1;
            Random random=new Random();
            do {
                idChiTiet = random.nextInt(1000000000) + 1;
                if (chiTietCrud.findById(idChiTiet).orElse(null) != null) {
                    i = 2;
                } else {
                    i = 1;
                }
            } while (i > 1);
            System.out.println("id chi tiet"+idChiTiet);
            chitiet.setIddiaChi(idChiTiet).setTen(chitietEntity.getTen()).setSdt(chitietEntity.getSdt())
                    .setThanhpho(chitietEntity.getThanhpho()).setHuyen(chitietEntity.getHuyen())
                    .setXa(chitietEntity.getXa()).setChitiet(chitietEntity.getChitiet());
            chiTietCrud.save(chitiet);

            serviceRestfull.setData(chitiet);
        }


        return  serviceRestfull;
    }

    public ServiceRestfull getfindChiTiet(int idChiTiet){
        ServiceRestfull serviceRestfull=new ServiceRestfull();
        serviceRestfull.setData(chiTietCrud.findById(idChiTiet).orElse(null));
        return  serviceRestfull;
    }

}
