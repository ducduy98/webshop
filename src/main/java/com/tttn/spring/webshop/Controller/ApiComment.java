package com.tttn.spring.webshop.Controller;

import com.tttn.spring.webshop.domain.CommentCustom;
import com.tttn.spring.webshop.service.CommentService;
import com.tttn.spring.webshop.service.ServiceRestfull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Service;

@RestController
@RequestMapping("/apicomment")
public class ApiComment {
    @Autowired
    CommentService commentService;

    @GetMapping("/{id}")
    public ResponseEntity<ServiceRestfull> getComment(@PathVariable("id") int product){
        return new ResponseEntity<ServiceRestfull>(commentService.getlist(product), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ServiceRestfull> comment(@RequestBody CommentCustom comment){
        return new ResponseEntity<ServiceRestfull>(commentService.save(comment),HttpStatus.OK);
    }
}
