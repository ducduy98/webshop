package com.tttn.spring.webshop.unum;

public enum ColorEnum {

    Do,Cam,Vang,Luc,Lam,Tram,Tim,Den,Nau,Trang,Xam;


    @Override
    public String toString() {
        if(this==Do) return "đỏ";
        if(this==Cam) return "cam";
        if(this==Vang) return "vàng";
        if(this==Luc) return "lục";
        if(this==Lam) return "lam";
        if(this==Tram) return "tràm";
        if(this==Tim) return "tím";
        if(this==Den) return "đen";
        if(this==Nau) return "nâu";
        if(this==Trang) return "trắng";
        else return "xám";
    }
}
