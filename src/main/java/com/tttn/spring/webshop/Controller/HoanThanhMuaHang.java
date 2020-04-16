package com.tttn.spring.webshop.Controller;

import com.tttn.spring.webshop.Crud.ChiTietCrud;
import com.tttn.spring.webshop.model.ChitietEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@Controller
@RequestMapping("/themdiachi")
public class HoanThanhMuaHang {

    @Autowired
    ChiTietCrud chiTietCrud;

    @PostMapping
    @ResponseBody
    public ChitietEntity themDiaChi(@RequestBody ChitietEntity chitietEntity){
        int idChiTiet;
        int i = 1;
        Random random=new Random();
        do {
            idChiTiet = random.nextInt(1000000000) + 1;
            if (chiTietCrud.findById(idChiTiet).orElse(null) != null) {
                i = 2;
            } else {
                i = 1;
            }
        } while (i > 1);
        chitietEntity.setIddiaChi(idChiTiet);
        chiTietCrud.save(chitietEntity);
        return chitietEntity;
    }
}
