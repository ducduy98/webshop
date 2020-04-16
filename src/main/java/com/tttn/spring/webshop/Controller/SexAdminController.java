package com.tttn.spring.webshop.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/quanlidanhmuc")
public class SexAdminController {

    @GetMapping
    public String defaul(){
        return "categoryAdmin";
    }
}
