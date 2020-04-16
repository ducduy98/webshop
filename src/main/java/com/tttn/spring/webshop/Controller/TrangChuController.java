package com.tttn.spring.webshop.Controller;

import com.tttn.spring.webshop.Crud.*;
import com.tttn.spring.webshop.model.*;
import com.tttn.spring.webshop.service.LoggerExample;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/")
@SessionAttributes("myCart")

public class TrangChuController {

    @Autowired
    CategoryCrud categoryCrud;

    @Autowired
    ProductSaleCrud productSaleCrud;

    @Autowired
    CartCrud cartCrud;

    @Autowired
    SexCrud sexCrud;

    @Autowired
    ProductCrud productCrud;

    @Autowired
    TaikhoanCrud taikhoanCrud;

    @Autowired
    LoggerExample logger;

    @Autowired
    CodeCrud codeCrud;



    

    @GetMapping("/trangchu")
    public String home(Model model, HttpServletRequest request){
        Object principal= SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String id=null;
        if(principal instanceof UserDetails){
            id=((UserDetails) principal).getUsername();
        }
        if(id!=null){
            model.addAttribute("user",1);
            TaikhoanEntity taikhoanEntity=taikhoanCrud.findById(id).orElse(null);
            List<RoleEntity> listRole=taikhoanEntity.getTaikhoanRolesById().stream().map(c->c.getRoleByRole()).filter(c->c.getIdrole()==1 || c.getIdrole()==3 || c.getIdrole()==4).collect(Collectors.toList());
            if(listRole.size()>0){
                model.addAttribute("admin",1);
            }
            List<CartEntity> listC2=cartCrud.getListCart(id);
            int size=listC2.size();
            model.addAttribute("sizeCart",size);
            model.addAttribute("filter",1);
            model.addAttribute("code",getMa());
            model.addAttribute("soluongcode",getMa().size());

        }
        else if(id==null){
            HttpSession session =request.getSession();
            if(session.getAttribute("myCart")==null){
                List<CartEntity> listCarts=new ArrayList<CartEntity>();

                model.addAttribute("myCart",listCarts);
                logger.info("khoi tao my cart khong dung tai khoan");
                model.addAttribute("sizeCart",0);
            }else{
                List<CartEntity> listC1= (List<CartEntity>) session.getAttribute("myCart");
                int size=listC1.size();
                model.addAttribute("sizeCart",size);
            }
        }

        List<SexEntity> gioiTinhs= (List<SexEntity>) sexCrud.findAll();
        List<ProductSaleEntity> sales=productSaleCrud.getSaleProduct();
        Pageable pageable=PageRequest.of(0,7);
        List<ProductEntity> buyMany=productCrud.buyMany(pageable);


        buyMany.forEach(b->{
            System.out.println(b.getStatus());
        });
        model.addAttribute("buyMany",buyMany);
        model.addAttribute("productSales",sales);
        model.addAttribute("listSex",gioiTinhs);
        return "trangchu";
    }

    public List<CodeEntity> getMa(){
        return codeCrud.findStatus1();
    }
}
