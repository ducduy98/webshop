package com.tttn.spring.webshop.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/changepass")
public class ChangePassController {

    @GetMapping
    public String defaulView(){
        return "changepass";
    }
}
