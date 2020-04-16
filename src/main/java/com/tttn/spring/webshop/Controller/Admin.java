package com.tttn.spring.webshop.Controller;

import com.tttn.spring.webshop.Crud.CategoryCrud;
import com.tttn.spring.webshop.model.CategoryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/homeAdmin")
public class Admin {
    @Autowired
    CategoryCrud categoryCrud;

    @GetMapping
    public String admin(Model model){

        List<CategoryEntity> list= (List<CategoryEntity>) categoryCrud.findAll();
        model.addAttribute("list",list);
        return "adminHome";
    }
}
