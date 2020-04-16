package com.tttn.spring.webshop.Controller;

import com.tttn.spring.webshop.Crud.CodeCrud;
import com.tttn.spring.webshop.domain.CodeDomain;
import com.tttn.spring.webshop.service.CodeService;
import com.tttn.spring.webshop.service.ServiceRestfull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.ws.Service;

@RestController
@RequestMapping("/apicode")
public class CodeApi {

    @Autowired
    CodeService codeService;

    @GetMapping
    public ResponseEntity<ServiceRestfull> getStatus1(){
        return new ResponseEntity<ServiceRestfull>(codeService.getlist(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ServiceRestfull> add(@Valid @RequestBody CodeDomain codeDomain){
        return new ResponseEntity<ServiceRestfull>(codeService.save(codeDomain),HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceRestfull> update(@PathVariable("id") int id){
        return new ResponseEntity<ServiceRestfull>(codeService.deleteTam(id),HttpStatus.OK);
    }

    @GetMapping("/status")
    public ResponseEntity<ServiceRestfull> getStatus2(){
        return new ResponseEntity<ServiceRestfull>(codeService.getListSatatus2(), HttpStatus.OK);
    }

}
