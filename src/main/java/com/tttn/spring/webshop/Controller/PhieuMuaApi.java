package com.tttn.spring.webshop.Controller;

import com.tttn.spring.webshop.service.PhieuMuaService;
import com.tttn.spring.webshop.service.ServiceRestfull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Service;

@RestController
@RequestMapping("/apiphieumua/phieumua")
public class PhieuMuaApi {
    @Autowired
    PhieuMuaService phieuMuaService;

    @GetMapping("/{status}")
    public ResponseEntity<ServiceRestfull> getPhieuMua(@PathVariable("status") int status){
        return new ResponseEntity<ServiceRestfull>(phieuMuaService.findStatus(status), HttpStatus.OK);
    }

    @GetMapping("/xacthuc/{status}/{maphieu}")
    public ResponseEntity<ServiceRestfull> updatePhieuMua(@PathVariable("status") int status,
                                                          @PathVariable("maphieu") String maphieu)
    {
        return new ResponseEntity<ServiceRestfull>(phieuMuaService.update(status,maphieu),HttpStatus.OK);
    }
}
