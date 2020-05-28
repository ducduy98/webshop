package com.tttn.spring.webshop.Controller;

import com.tttn.spring.webshop.Crud.CategoryCrud;
import com.tttn.spring.webshop.domain.Search;
import com.tttn.spring.webshop.service.CategoryService;
import com.tttn.spring.webshop.service.ProductService;
import com.tttn.spring.webshop.service.ServiceRestfull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("apishowmore")
public class ApiShowMore {
    @Autowired
    CategoryService categoryService;

    @Autowired
    ProductService productService;

    @GetMapping("/{id}/{status}")
    public ResponseEntity<ServiceRestfull> showmore(@PathVariable("id") int id, @PathVariable("status") int status) {
        System.out.println(id + " " + status);
        ServiceRestfull kq = categoryService.showMore(id, status);
        System.out.println("kq"+ kq.toString());
        return new ResponseEntity<ServiceRestfull>(kq, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ServiceRestfull> timkiem(@RequestBody Search s) {
        return new ResponseEntity<ServiceRestfull>(productService.timkiem(s), HttpStatus.OK);
    }
}
