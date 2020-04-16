package com.tttn.spring.webshop.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/loginShop")
public class LoginController {


    @GetMapping
    public String login(Model model, @RequestParam(value = "error",required = false,defaultValue = "") String error){

        if(!"".equals(error)){
           model.addAttribute("message","dang nhap that bai");
        }
        return "login";
    }
}
