package com.tttn.spring.webshop.Controller;

import com.tttn.spring.webshop.domain.TaiKhoanDomain;
import com.tttn.spring.webshop.service.NhanVienService;
import com.tttn.spring.webshop.service.ServiceRestfull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/apinhanvien")
public class ApiNhanVien {
    @Autowired
    NhanVienService nhanVienService;

    @PostMapping
    public ResponseEntity<ServiceRestfull> check(@RequestBody TaiKhoanDomain taiKhoanDomain){
        return new ResponseEntity<ServiceRestfull>(nhanVienService.checkUser(taiKhoanDomain), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ServiceRestfull> update(@RequestBody TaiKhoanDomain taiKhoanDomain){
        return new ResponseEntity<ServiceRestfull>(nhanVienService.update(taiKhoanDomain), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ServiceRestfull> getlist(){
        return new ResponseEntity<ServiceRestfull>(nhanVienService.getlist(), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<ServiceRestfull> add(@RequestBody TaiKhoanDomain taiKhoanDomain){
        return new ResponseEntity<ServiceRestfull>(nhanVienService.save(taiKhoanDomain), HttpStatus.OK);
    }

    @PutMapping("/locked")
    public ResponseEntity<ServiceRestfull> locked(@RequestBody TaiKhoanDomain taiKhoanDomain){
        return new ResponseEntity<ServiceRestfull>(nhanVienService.locked(taiKhoanDomain), HttpStatus.OK);
    }
}
