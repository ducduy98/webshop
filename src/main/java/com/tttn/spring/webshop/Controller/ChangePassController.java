package com.tttn.spring.webshop.Controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/changepass")
public class ChangePassController {

    @GetMapping
    public String defaulView(Model model){
        Object principal= SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String id=null;
        if(principal instanceof UserDetails){
             id=((UserDetails) principal).getUsername();
        }
        model.addAttribute("acc",id);

        return "changepass";

    }
}
