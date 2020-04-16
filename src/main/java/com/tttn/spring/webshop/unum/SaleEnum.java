package com.tttn.spring.webshop.unum;

public enum SaleEnum {
    HoatDong,DungHoatDong,Not;


    @Override
    public String toString() {
        if(this==HoatDong){
            return "Hoat Dong";
        }
        else if(this==DungHoatDong) return "Dung Hoat Dong";
        else return "404";
    }
}
