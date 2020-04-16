package com.tttn.spring.webshop.service;

import com.tttn.spring.webshop.Crud.TaikhoanCrud;
import com.tttn.spring.webshop.model.RoleEntity;
import com.tttn.spring.webshop.model.TaikhoanEntity;
import com.tttn.spring.webshop.model.TaikhoanRoleEntity;
import com.tttn.spring.webshop.unum.EnumService;
import com.tttn.spring.webshop.unum.RoleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class TaiKhoanService implements UserDetailsService {

    @Autowired
    EnumService enumService;

    @Autowired
    TaikhoanCrud taikhoanCrud;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        TaikhoanEntity taikhoan=taikhoanCrud.findById(s).orElse(null);

        if(taikhoan==null){
            throw new UsernameNotFoundException("not found user name!!");
        }
        Set<GrantedAuthority> grantedAuthorities=new HashSet<>();

        List<TaikhoanRoleEntity> listtaikhoanRole= (List<TaikhoanRoleEntity>) taikhoan.getTaikhoanRolesById();
        for(TaikhoanRoleEntity taikhoanrole:listtaikhoanRole){
            grantedAuthorities.add(new SimpleGrantedAuthority(taikhoanrole.getRoleByRole().getNameRole()));
        }






        return new UserDetail(taikhoan);
    }


}
