package com.tttn.spring.webshop.unum;

import javax.management.relation.Role;

public enum RoleEnum {
    Admin,NguoiDung,QuanLi,NhanVien,FalseNot;


    @Override
    public String toString() {
        if(this==Admin){
            return "Admin";
        }
        if(this==NguoiDung){
            return "NguoiDung";
        }
        if(this==QuanLi){
            return "QuanLi";
        }
        if(this==NhanVien) return "NhanVien";
        else{
            return "Not";
        }
    }
}
