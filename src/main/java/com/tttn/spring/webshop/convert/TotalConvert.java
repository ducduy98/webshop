package com.tttn.spring.webshop.convert;

import com.tttn.spring.webshop.dto.DashboardTotal;
import com.tttn.spring.webshop.model.PhieumuaEntity;

import java.util.List;


public class TotalConvert {

    public static DashboardTotal getConvert(String date,List<PhieumuaEntity> list){
        DashboardTotal total=new DashboardTotal();
        total.setDate(date);
        Integer thanhTien=list.stream().mapToInt(c->c.getPhieumuaCartsByMaPhieu()
                .stream().mapToInt(pm->pm.getCartByCart().getThanhtien()*pm.getCartByCart().getSoluong()).sum()
        ).sum();
        total.setTotal(thanhTien);

        return total;
    }
}
