package com.tttn.spring.webshop.Controller;

import com.tttn.spring.webshop.model.ChiTietJson;
import com.tttn.spring.webshop.model.ChitietEntity;
import com.tttn.spring.webshop.service.Restfull;
import com.tttn.spring.webshop.service.ServiceRestfull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class Api {
    @Autowired
    Restfull restfull;

    @GetMapping("/thanhpho")
    public ResponseEntity<ServiceRestfull> getThanhPho(){
        return new ResponseEntity<ServiceRestfull>(restfull.getThanhPho(), HttpStatus.OK);
    }

    @GetMapping("/thanhpho/gethuyen/{id}")
    public ResponseEntity<ServiceRestfull> getThanhPho(@PathVariable("id") String id){
        return new ResponseEntity<ServiceRestfull>(restfull.getHuyen(id), HttpStatus.OK);
    }
    @GetMapping("/thanhpho/gethuyen/getxa/{id}")
    public ResponseEntity<ServiceRestfull> getXa(@PathVariable("id") String id){
        return new ResponseEntity<ServiceRestfull>(restfull.getXa(id), HttpStatus.OK);
    }

    @PostMapping(value="/themchitiet",consumes={"application/json"})
    public ResponseEntity<ServiceRestfull> them(@RequestBody ChiTietJson chitietEntity){
        //ChitietEntity chitietEntity=new ChitietEntity();
        //chitietEntity.setChitiet(chitiet).setTen(hoten).setSdt(sdt).setHuyen(huyen).setXa(xa).setThanhpho(thanhpho);
        return new ResponseEntity<ServiceRestfull>(restfull.saveChiTiet(chitietEntity), HttpStatus.OK);
    }

    @GetMapping(value="/themchitiet/{id}")
    public ResponseEntity<ServiceRestfull> themid(@PathVariable("id") int idChiTiet){
        //ChitietEntity chitietEntity=new ChitietEntity();
        //chitietEntity.setChitiet(chitiet).setTen(hoten).setSdt(sdt).setHuyen(huyen).setXa(xa).setThanhpho(thanhpho);
        return new ResponseEntity<ServiceRestfull>(restfull.getfindChiTiet(idChiTiet), HttpStatus.OK);
    }

}
