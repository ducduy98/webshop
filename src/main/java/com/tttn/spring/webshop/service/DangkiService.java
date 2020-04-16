package com.tttn.spring.webshop.service;

import com.tttn.spring.webshop.Crud.RoleCrud;
import com.tttn.spring.webshop.Crud.TaiKhoanRoleCrud;
import com.tttn.spring.webshop.Crud.TaikhoanCrud;
import com.tttn.spring.webshop.exception.SaveExceptionJPA;
import com.tttn.spring.webshop.model.RoleEntity;
import com.tttn.spring.webshop.model.TaikhoanEntity;
import com.tttn.spring.webshop.model.TaikhoanRoleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DangkiService {

    @Autowired
    TaikhoanCrud taikhoanCrud;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RoleCrud roleCrud;
    @Autowired
    TaiKhoanRoleCrud taiKhoanRoleCrud;

    @Transactional(rollbackFor = SaveExceptionJPA.class)
    public int dangki(String user,String pass1, String pass2){
        if(null!=taikhoanCrud.findById(user).orElse(null)){
            return 1;
        }
        else if(!pass1.equals(pass2)){
            return 2;
        }
        try{
            TaikhoanEntity taikhoanEntity=new TaikhoanEntity();
            taikhoanEntity.setId(user);
            taikhoanEntity.setPass(passwordEncoder.encode(pass1));
            taikhoanCrud.save(taikhoanEntity);
            RoleEntity roleEntity=roleCrud.findById(2).orElse(null);
            TaikhoanRoleEntity taikhoanRoleEntity=new TaikhoanRoleEntity();
            taikhoanRoleEntity.setTaikhoanByTaikhoan(taikhoanCrud.findById(user).orElse(null));
            taikhoanRoleEntity.setRoleByRole(roleEntity);
            taiKhoanRoleCrud.save(taikhoanRoleEntity);

        }catch (Exception ex){
            try {
                throw  new SaveExceptionJPA("roll back");
            } catch (SaveExceptionJPA saveExceptionJPA) {
                saveExceptionJPA.printStackTrace();
            }

        }

        return 3;
    }

}
