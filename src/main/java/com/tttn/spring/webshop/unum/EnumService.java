package com.tttn.spring.webshop.unum;


import org.springframework.stereotype.Service;

import java.awt.*;

@Service
public class EnumService {

    public RoleEnum getQuyen(int status){
        switch (status){
            case 1: return RoleEnum.Admin;
            case 2 : return RoleEnum.NguoiDung;
            case 3 : return RoleEnum.QuanLi;
            case 4 : return RoleEnum.NhanVien;
        }
        return RoleEnum.FalseNot;
    }

    public SaleEnum getSale(int status){
        switch (status){
            case 1: return SaleEnum.HoatDong;
            case 2:return SaleEnum.DungHoatDong;
        }
        return SaleEnum.Not;
    }
    public SexEnum getSex(int status){
        switch(status){
            case 1: return SexEnum.Nam;
            case 2 : return SexEnum.Nu;
        }
        return null;
    }

    public ColorEnum getColor(int status){
        switch (status){
            case 1: return ColorEnum.Do;
            case 2 :return ColorEnum.Cam;
            case 3 :return ColorEnum.Vang;
            case 4 :return ColorEnum.Luc;
            case 5 : return ColorEnum.Lam;
            case 6 : return ColorEnum.Tram;
            case 7 : return ColorEnum.Tim;
            case 8 : return ColorEnum.Den;
            case 9 : return ColorEnum.Nau;
            case 10 : return ColorEnum.Trang;
            case 11 : return ColorEnum.Xam;
        }

        return null;
    }

}
