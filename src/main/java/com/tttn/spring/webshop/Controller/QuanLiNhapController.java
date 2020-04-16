package com.tttn.spring.webshop.Controller;


import com.tttn.spring.webshop.Crud.NhapHangCrud;
import com.tttn.spring.webshop.model.NhapHangEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/quanlinhap")
public class QuanLiNhapController {
    @Autowired
    NhapHangCrud nhapHangCrud;

    @GetMapping
    public String quanlinhap(Model model){

        List<NhapHangEntity> list= (List<NhapHangEntity>) nhapHangCrud.findAll();
        list.sort(new Comparator<NhapHangEntity>() {
            @Override
            public int compare(NhapHangEntity o1, NhapHangEntity o2) {
                return o2.getNgaynhap().compareTo(o1.getNgaynhap());
            }
        });

        model.addAttribute("list",list);

        return "quanlinhap";
    }

    @GetMapping("/{id}")
    public String chitiet(@PathVariable("id") String id,Model model){
        NhapHangEntity nhap=nhapHangCrud.findById(id).orElse(null);
        model.addAttribute("phieu",nhap);
        return "chitietnhap";
    }
}
