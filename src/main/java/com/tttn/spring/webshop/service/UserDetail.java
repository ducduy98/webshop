package com.tttn.spring.webshop.service;

import com.tttn.spring.webshop.model.TaikhoanEntity;
import com.tttn.spring.webshop.model.TaikhoanRoleEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserDetail implements UserDetails {
    private TaikhoanEntity taikhoan;

    public UserDetail(TaikhoanEntity taikhoanEntity) {
        this.taikhoan = taikhoanEntity;
    }

    public TaikhoanEntity getTaikhoan() {
        return taikhoan;
    }

    public UserDetail setTaikhoan(TaikhoanEntity taikhoan) {
        this.taikhoan = taikhoan;
        return this;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> grantedAuthorities=new HashSet<>();

        List<TaikhoanRoleEntity> listtaikhoanRole= (List<TaikhoanRoleEntity>) taikhoan.getTaikhoanRolesById();
        for(TaikhoanRoleEntity taikhoanrole:listtaikhoanRole){
            grantedAuthorities.add(new SimpleGrantedAuthority(taikhoanrole.getRoleByRole().getNameRole()));
        }
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return taikhoan.getPass();
    }

    @Override
    public String getUsername() {
        return taikhoan.getId();
    }

    @Override
    public boolean isAccountNonExpired() {

        return true;
    }

    @Override
    public boolean isAccountNonLocked() {

        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {

        return true;
    }

    @Override
    public boolean isEnabled() {

        return true;
    }
}
