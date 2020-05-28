package com.tttn.spring.webshop.service;

import com.tttn.spring.webshop.Crud.RoleCrud;
import com.tttn.spring.webshop.Crud.TaiKhoanRoleCrud;
import com.tttn.spring.webshop.Crud.TaikhoanCrud;
import com.tttn.spring.webshop.domain.TaiKhoanDomain;
import com.tttn.spring.webshop.exception.SaveExceptionJPA;
import com.tttn.spring.webshop.model.TaikhoanEntity;
import com.tttn.spring.webshop.model.TaikhoanRoleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class NhanVienService {

    @Autowired
    TaikhoanCrud taikhoanCrud;

    @Autowired
    TaiKhoanRoleCrud taiKhoanRoleCrud;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    RoleCrud roleCrud;

    public ServiceRestfull getlist() {
        ServiceRestfull serviceRestfull = new ServiceRestfull();
        List<TaikhoanEntity> taikhoans = taiKhoanRoleCrud.getNhanVien().stream().map(t -> t.getTaikhoanByTaikhoan()).collect(Collectors.toList());
        serviceRestfull.setData(taikhoans);
        return serviceRestfull;
    }


    @Transactional(rollbackFor = SaveExceptionJPA.class)
    public ServiceRestfull save(TaiKhoanDomain tk) {
        ServiceRestfull serviceRestfull = new ServiceRestfull();
        if (null != taikhoanCrud.findById(tk.getId()).orElse(null)) {
            serviceRestfull.setMessage("loi");
        } else {
            try {
                TaikhoanEntity taikhoanEntity = new TaikhoanEntity();
                taikhoanEntity.setId(tk.getId());
                taikhoanEntity.setPass(passwordEncoder.encode(tk.getPass()));
                taikhoanEntity.setStatus(1);
                taikhoanCrud.save(taikhoanEntity);
                TaikhoanRoleEntity taikhoanRoleEntity = new TaikhoanRoleEntity();
                taikhoanRoleEntity.setRoleByRole(roleCrud.findById(3).orElse(null));
                taikhoanRoleEntity.setTaikhoanByTaikhoan(taikhoanCrud.findById(tk.getId()).orElse(null));
                taiKhoanRoleCrud.save(taikhoanRoleEntity);
            } catch (Exception e) {
                try {
                    throw new SaveExceptionJPA("hi");
                } catch (SaveExceptionJPA saveExceptionJPA) {
                    saveExceptionJPA.printStackTrace();
                }
            }
        }

        serviceRestfull = getlist();
        return serviceRestfull;
    }

    public ServiceRestfull update(TaiKhoanDomain tk) {
        ServiceRestfull serviceRestfull = new ServiceRestfull();
        TaikhoanEntity taikhoanEntity = taikhoanCrud.findById(tk.getId()).orElse(null);
        System.out.println("id" + tk.getId()
        );
        System.out.println("pass" + tk.getPass());
        taikhoanEntity.setPass(passwordEncoder.encode(tk.getPass()));
        taikhoanCrud.save(taikhoanEntity);
        return serviceRestfull;
    }

    public ServiceRestfull checkUser(TaiKhoanDomain tk) {
        ServiceRestfull serviceRestfull = new ServiceRestfull();
        TaikhoanEntity taikhoanEntity = taikhoanCrud.findById(tk.getId()).orElse(null);
        try {
            Authentication au = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    tk.getId(), tk.getPass()
            ));

            serviceRestfull.setMessage("Success");
        } catch (Exception ex) {
            serviceRestfull.setMessage("Kiểm tra lại tài khoản và mật khẩu");
        }
        return serviceRestfull;

    }

    public ServiceRestfull locked(TaiKhoanDomain tk) {

        TaikhoanEntity taikhoanEntity = taikhoanCrud.findById(tk.getId()).orElse(null);
        System.out.println("id" + tk.getId()
        );
        System.out.println("pass" + tk.getPass());
        taikhoanEntity.setStatus(2);
        taikhoanEntity.setPass(passwordEncoder.encode(String.valueOf(timPass())));
        taikhoanCrud.save(taikhoanEntity);
        ServiceRestfull serviceRestfull = getlist();
        return serviceRestfull;
    }

    public int timPass(){

        Random rd=new Random();
        int id=rd.nextInt(10000)+1;
        return id;
    }
}
