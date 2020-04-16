package com.tttn.spring.webshop.Controller;

import com.tttn.spring.webshop.service.ProductSizeColorService;
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
@RequestMapping("/apikho")
public class ApiKho {
    @Autowired
    ProductSizeColorService ps;

    @GetMapping
    public ResponseEntity<ServiceRestfull> getlist(){
        return new ResponseEntity<ServiceRestfull>(ps.getList(), HttpStatus.OK);
    }

    @GetMapping("/{id}/{size}/{color}")
    public ResponseEntity<ServiceRestfull> search(@PathVariable("id") int id,@PathVariable("size") int size,
                                                  @PathVariable("color") int color){
        return new ResponseEntity<ServiceRestfull>(ps.search(id, size, color),HttpStatus.OK);
    }

    @GetMapping("/update/{id}/{soluong}")
    public ResponseEntity<ServiceRestfull> update(@PathVariable("id") int id,@PathVariable("soluong") int soluong){

        return new ResponseEntity<ServiceRestfull>(ps.update(soluong,id),HttpStatus.OK);
    }
}
