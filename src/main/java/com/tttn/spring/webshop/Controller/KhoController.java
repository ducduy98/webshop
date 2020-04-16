package com.tttn.spring.webshop.Controller;

import com.tttn.spring.webshop.Crud.SizeCrud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("quanlikho")
public class KhoController {
    @Autowired
    SizeCrud sizeCrud;

    @GetMapping
    public String kho(Model model){
        model.addAttribute("size",sizeCrud.findAll());
        return "quanlikho";
    }
}
