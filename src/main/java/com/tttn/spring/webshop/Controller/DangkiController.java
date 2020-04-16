package com.tttn.spring.webshop.Controller;

import com.tttn.spring.webshop.service.DangkiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/dangki")
public class DangkiController {

    @Autowired
    DangkiService dangkiService;

    @GetMapping
    public String defaul(Model model
            ,@RequestParam(value = "message",required = false,defaultValue = "") String thongbao){
        model.addAttribute("mesage",thongbao);
        return "dangki";
    }

    @PostMapping
    public String dangki(@RequestParam("username") String username, @RequestParam("pass1") String pass1,
                         @RequestParam("pass2") String pass2, Model model){

        int ketqua=dangkiService.dangki(username, pass1, pass2);
        if(1==ketqua){
            String message="tai khoan da ton tai";
            return "redirect:/dangki?message="+message;
        }else if(2==ketqua){
            String message="nhap lai pass khong dung";
            return "redirect:/dangki?message="+message;

        }else
            return "redirect:/loginShop";
    }


}
