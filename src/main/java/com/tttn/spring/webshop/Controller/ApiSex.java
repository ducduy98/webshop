package com.tttn.spring.webshop.Controller;

import com.tttn.spring.webshop.model.SexCategoryCustom;
import com.tttn.spring.webshop.service.CategoryService;
import com.tttn.spring.webshop.service.ServiceRestfull;
import com.tttn.spring.webshop.service.SexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/apisex/sex")
public class ApiSex {
    @Autowired
    SexService sexService;

    @Autowired
    CategoryService categoryService;

    @GetMapping
    public ResponseEntity<ServiceRestfull> getlist(){
        return new ResponseEntity<ServiceRestfull>(sexService.getList(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ServiceRestfull> save(@RequestBody SexCategoryCustom sexCategoryCustom){
        return new ResponseEntity<ServiceRestfull>(sexService.save(sexCategoryCustom), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ServiceRestfull> update(@RequestBody SexCategoryCustom sexCategoryCustom){
        return new ResponseEntity<ServiceRestfull>(sexService.update(sexCategoryCustom), HttpStatus.OK);
    }

    @PostMapping("/category")
    public ResponseEntity<ServiceRestfull> saveCategory(@RequestBody SexCategoryCustom sexCategoryCustom){
        return new ResponseEntity<ServiceRestfull>(sexService.saveCategory(sexCategoryCustom), HttpStatus.OK);
    }

    @PutMapping("/category")
    public ResponseEntity<ServiceRestfull> updateCategory(@RequestBody SexCategoryCustom sexCategoryCustom){
        return new ResponseEntity<ServiceRestfull>(sexService.updateCategory(sexCategoryCustom), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceRestfull> findId(@PathVariable("id") int id){
        return new ResponseEntity<ServiceRestfull>(sexService.findId(id),HttpStatus.OK);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<ServiceRestfull> findIdCategory(@PathVariable("id") int id){
        return new ResponseEntity<ServiceRestfull>(categoryService.IdFind(id),HttpStatus.OK);
    }


}
