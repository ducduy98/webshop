package com.tttn.spring.webshop.Controller;

import com.tttn.spring.webshop.Crud.CartCrud;
import com.tttn.spring.webshop.model.CartEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/showmore")
@SessionAttributes("myCart")
public class ShowMoreController {
    @Autowired
    CartCrud cartCrud;

    @GetMapping("/{id}/*")
    public String defaul(@PathVariable("id") int idc, HttpServletRequest request, Model model){

        Object principal= SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String id=null;
        if(principal instanceof UserDetails){
            id=((UserDetails) principal).getUsername();
        }
        if(id!=null){
            List<CartEntity> listC2=cartCrud.getListCart(id);
            int size=listC2.size();
            model.addAttribute("sizeCart",size);
        }
        else if(id==null){
            HttpSession session =request.getSession();
            if(session.getAttribute("myCart")==null){
                List<CartEntity> listCarts=new ArrayList<CartEntity>();

                model.addAttribute("myCart",listCarts);
                // logger.info("khoi tao my cart khong dung tai khoan");
                model.addAttribute("sizeCart",0);
            }else{
                List<CartEntity> listC1= (List<CartEntity>) session.getAttribute("myCart");
                int size=listC1.size();
                model.addAttribute("sizeCart",size);
            }
        }
        model.addAttribute("id",idc);

        return "showmore";
    }
}
