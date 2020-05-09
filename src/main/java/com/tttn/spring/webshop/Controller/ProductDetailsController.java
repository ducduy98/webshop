package com.tttn.spring.webshop.Controller;


import com.tttn.spring.webshop.Crud.CartCrud;
import com.tttn.spring.webshop.Crud.HinhAnhCrud;
import com.tttn.spring.webshop.Crud.PhieuMuaCartCrud;
import com.tttn.spring.webshop.Crud.ProductCrud;
import com.tttn.spring.webshop.model.CartEntity;
import com.tttn.spring.webshop.model.HinhanhEntity;
import com.tttn.spring.webshop.model.PhieumuaCartEntity;
import com.tttn.spring.webshop.model.ProductEntity;
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
@RequestMapping("/product_details")
@SessionAttributes("myCart")
public class ProductDetailsController {

    @Autowired
    ProductCrud productCrud;

    @Autowired
    PhieuMuaCartCrud phieumuaCart;

    @Autowired
    HinhAnhCrud hinhAnhCrud;

    @Autowired
    CartCrud cartCrud;


    @GetMapping("/{idProduct}/*")
    public String getDetails(@PathVariable("idProduct") int idProduct, Model model, HttpServletRequest request){

        HttpSession session =request.getSession();
        if(session.getAttribute("myCart")==null){
            List<CartEntity> listCarts=new ArrayList<CartEntity>();
            model.addAttribute("myCart",listCarts);
            model.addAttribute("sizeCart",0);
        }else{
            List<CartEntity> listC1= (List<CartEntity>) session.getAttribute("myCart");
            int size=listC1.size();
            model.addAttribute("sizeCart",size);
        }
        ProductEntity productEntity =productCrud.findById(idProduct).orElse(null);
        List<HinhanhEntity> hinhAnhs=hinhAnhCrud.getHinhAnhProduct(idProduct);

        Object princial= SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String id=null;
        if(princial instanceof UserDetails){
             id=((UserDetails) princial).getUsername();
        }

        if(id != null){
            List<PhieumuaCartEntity> phieumuaCartEntities=phieumuaCart.getPhieuMua(id,idProduct);
            if(phieumuaCartEntities.size()>0) model.addAttribute("giatri",1);
            else model.addAttribute("giatri",2);
            List<CartEntity> listC2=cartCrud.getListCart(id);
            int size=listC2.size();
            model.addAttribute("sizeCart",size);

        }
        else model.addAttribute("giatri",2);


        model.addAttribute("hinhAnhs",hinhAnhs);
        model.addAttribute("productEntiti",productEntity);
        return "chiTietProduct";

    }
}
