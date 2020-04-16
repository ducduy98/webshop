package com.tttn.spring.webshop.Controller;


import com.tttn.spring.webshop.model.ProductCustom;
import com.tttn.spring.webshop.model.ProductEntity;
import com.tttn.spring.webshop.service.ProductService;
import com.tttn.spring.webshop.service.ServiceRestfull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/apiAdmin/product")
public class ApiAdmin {

    @Autowired
    ProductService  productService;

    @GetMapping
    public ResponseEntity<ServiceRestfull> getAll(){
        return new ResponseEntity<ServiceRestfull>(productService.getList(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceRestfull> getAll(@PathVariable("id") int id){
        return new ResponseEntity<ServiceRestfull>(productService.getId(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ServiceRestfull> save(@RequestBody ProductCustom product){
        return new ResponseEntity<ServiceRestfull>(productService.save(product), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ServiceRestfull>  update(@RequestBody ProductCustom  product){
        return new ResponseEntity<ServiceRestfull>(productService.update(product), HttpStatus.OK);
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<ServiceRestfull> delete(@PathVariable("id") int id){
        return new ResponseEntity<ServiceRestfull>(productService.delete(id),HttpStatus.OK);
    }
}
