package com.tttn.spring.webshop.unum;

public enum SexEnum {

    Nam, Nu;


    @Override
    public String toString() {
        if(this==Nam) return "Nam";
        else return "Nu";
    }
}
