package com.tttn.spring.webshop.Controller;

import com.tttn.spring.webshop.Crud.NhapHangCrud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Random;

@Controller
@RequestMapping("/nhaphang")
public class NhapHangController {
    @Autowired
    NhapHangCrud nhapHangCrud;

    @GetMapping
    public String defaul(Model model){
        model.addAttribute("maphieu",timId());
        return "nhaphang";
    }

    @GetMapping("/back")
    public String back(){
        return "redirect:/nhaphang";
    }

    public String timId(){
        int id=0;
        Random random=new Random();
        while(id==0){
            id=random.nextInt(1_000_000_000)+1;
            if(null!=nhapHangCrud.findById("PM"+id).orElse(null)){
                id=0;
            }

        }

        return "PM"+id;
    }
}
