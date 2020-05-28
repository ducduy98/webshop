package com.tttn.spring.webshop.Controller;

import com.tttn.spring.webshop.domain.TaiKhoanDomain;
import com.tttn.spring.webshop.service.NhanVienService;
import com.tttn.spring.webshop.service.ServiceRestfull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/apinhanvien")
public class ApiNhanVien {
    @Autowired
    NhanVienService nhanVienService;

    @PostMapping
    @ResponseBody
    public ResponseEntity<ServiceRestfull> check(@RequestBody TaiKhoanDomain taiKhoanDomain){
        return new ResponseEntity<ServiceRestfull>(nhanVienService.checkUser(taiKhoanDomain), HttpStatus.OK);
    }

    @PostMapping("/update")
    public String update(TaiKhoanDomain taiKhoanDomain){
        System.out.println("update pw");
        ServiceRestfull kq = nhanVienService.update(taiKhoanDomain);
        System.out.println(kq);
        return "redirect:/logout";
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<ServiceRestfull> getlist(){
        return new ResponseEntity<ServiceRestfull>(nhanVienService.getlist(), HttpStatus.OK);
    }

    @PostMapping("/add")
    @ResponseBody
    public ResponseEntity<ServiceRestfull> add(@RequestBody TaiKhoanDomain taiKhoanDomain){
        return new ResponseEntity<ServiceRestfull>(nhanVienService.save(taiKhoanDomain), HttpStatus.OK);
    }

    @PutMapping("/locked")
    @ResponseBody
    public ResponseEntity<ServiceRestfull> locked(@RequestBody TaiKhoanDomain taiKhoanDomain){
        return new ResponseEntity<ServiceRestfull>(nhanVienService.locked(taiKhoanDomain), HttpStatus.OK);
    }
}
