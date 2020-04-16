package com.tttn.spring.webshop.Controller;


import com.tttn.spring.webshop.Crud.*;
import com.tttn.spring.webshop.exception.SaveExceptionJPA;
import com.tttn.spring.webshop.model.*;
import com.tttn.spring.webshop.service.LoggerExample;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.security.Principal;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.*;
import java.util.List;

@Controller
@RequestMapping("/addToCart")
@SessionAttributes("myCart")
public class AddToCartController {


    @Autowired
    CartCrud cartCrud;

    @Autowired
    ColorCrud colorCrud;

    @Autowired
    SizeCrud sizeCrud;

    @Autowired
    ProductCrud productCrud;

    @Autowired
    LoggerExample logger;

    @Autowired
    ProductSizeColorCrud productSizeColorCrud;
    @Autowired
    TaikhoanCrud taikhoanCrud;

    @Autowired
    CodeCrud codeCrud;

    @ResponseBody
    @GetMapping("/{idProduct}/{soLuong}/{idColor}/{idSize}")
    public String addToCart(@PathVariable("idProduct") int idProduct, @PathVariable("soLuong") int soLuong,
                            @PathVariable(value = "idColor") int idColor, @PathVariable("idSize") int idSize,
                            HttpServletRequest rq, Model model) throws SaveExceptionJPA {
        // System.out.println("chay vao thu muc thanh cong id = "+idProduct+" so luong la : "+soLuong);
        int size = 0;
        Object principal = null;
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() != null) {
            principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        }
        String id = null;
        if (principal instanceof UserDetails) {
            id = ((UserDetails) principal).getUsername();
        }
        if (id != null) {
            CartEntity cartEntity = new CartEntity();
            Date dateUtils = DateUtils.addDays(Calendar.getInstance().getTime(), 0);

            //System.out.println("ngay hom nay "+DateFormatUtils.format(dateUtils,"yyyy-mm-dd"));
            Random random = new Random();
            int idCart;
            int i = 1;
            do {
                idCart = random.nextInt(1000000000) + 1;
                if (cartCrud.findById(idCart).orElse(null) != null) {
                    i = 2;
                } else {
                    i = 1;
                }
            } while (i > 1);
            ProductSizeColorEntity productSizeColorEntity=productSizeColorCrud.getChiTiet(idProduct, idSize, idColor);
            TaikhoanEntity taikhoanEntity=taikhoanCrud.findById(id).orElse(null);
            if (soLuong > productSizeColorEntity.getNumber()) {
                size = 0;
            } else {

                if(null==cartCrud.checkHang(id,productSizeColorEntity.getIdproductSizeColor())){
                    cartEntity.setDateCart(new Timestamp(dateUtils.getTime())).setIdcart(idCart).setTaikhoanByTaikhoan(taikhoanEntity)
                            .setStatus(1).setSoluong(soLuong).setProductSizeColorEntity(productSizeColorEntity)
                            .setThanhtien(productSizeColorEntity.getProductByProduct().getGiamGia());;
                    try {
                        cartCrud.save(cartEntity);
                        List<CartEntity> listCart = cartCrud.getListCart(id);
                        size = listCart.size();
                    } catch (Exception e) {
                        logger.error("loi update gio hang");
                        throw new SaveExceptionJPA("khong the update gio hang");
                    }
                }else{
                    CartEntity cartEntity1=cartCrud.checkHang(id,productSizeColorEntity.getIdproductSizeColor());
                    cartEntity1.setSoluong(cartEntity1.getSoluong()+soLuong);
                    try{
                        cartCrud.save(cartEntity1);
                        List<CartEntity> listCart = cartCrud.getListCart(id);
                        size = listCart.size();
                    }catch (Exception e){
                        logger.error("loi update gio hang");
                        throw new SaveExceptionJPA("khong the update gio hang");
                    }

                }

            }


        }
        // xu li khi khong co tai khoan
        else {
            HttpSession session = rq.getSession();
            if (session.getAttribute("myCart") == null) {
                List<CartEntity> listCarts = new ArrayList<CartEntity>();
                model.addAttribute("myCart", listCarts);
            } else {
                List<CartEntity> listC1 = (List<CartEntity>) session.getAttribute("myCart");
                CartEntity cartEntity = new CartEntity();
                Date dateUtils = DateUtils.addDays(Calendar.getInstance().getTime(), 0);

                //System.out.println("ngay hom nay "+DateFormatUtils.format(dateUtils,"yyyy-mm-dd"));
                Random random = new Random();
                int idCart;
                int i = 1;
                do {
                    idCart = random.nextInt(1000000000) + 1;
                    if (cartCrud.findById(idCart).orElse(null) != null) {
                        i = 2;
                    } else {
                        i = 1;
                    }
                } while (i > 1);
                ProductSizeColorEntity productSizeColorEntityNotUser=productSizeColorCrud.getChiTiet(idProduct, idSize, idColor);

                if (soLuong > productSizeColorEntityNotUser.getNumber()) {
                    size = 0;
                } else {
                   boolean checkFor1= listC1.stream().anyMatch(list-> {
                       return list.getProductSizeColorEntity().equals(productSizeColorEntityNotUser) && list.getStatus() == 1;
                   }
                    );

                   if(!checkFor1){
                       cartEntity.setDateCart(new Timestamp(dateUtils.getTime())).setIdcart(idCart)
                               .setProductSizeColorEntity(productSizeColorEntityNotUser)
                               .setStatus(1).setSoluong(soLuong).setThanhtien(productSizeColorEntityNotUser.getProductByProduct().getGiamGia());
                       try {
                           cartCrud.save(cartEntity);

                           listC1.add(cartEntity);
                           model.addAttribute("myCart", listC1);
                           size = listC1.size();
                       } catch (Exception e) {
                           logger.error("loi them gio hang khong user");
                           throw new SaveExceptionJPA("khong the insert du lieu");

                       }
                   }else{

                       for (CartEntity c: listC1) {
                           if(c.getProductSizeColorEntity().equals(productSizeColorEntityNotUser) && c.getStatus()==1){
                               logger.info("ton tai trong my cart");
                               CartEntity c2=c;
                               c2.setSoluong(c.getSoluong()+soLuong);
                               listC1.remove(c);
                               listC1.add(c2);
                               cartCrud.save(c2);
                           }
                       }
                        model.addAttribute("myCart",listC1);
                        size=listC1.size();
                   }

                }


            }

        }
        System.out.println("size la :" + size);
        return String.valueOf(size);

    }

    @GetMapping("/useSale/{sale}/{tongTien}")
    @ResponseBody
    public String addSale(@PathVariable("sale") String sale,@PathVariable("tongTien") int tongTien){
        CodeEntity code=codeCrud.codeExist(sale);
        System.out.println("tien la :"+tongTien +" sale ="+sale);
        System.out.println("giam gia :"+code.getGiamGia());
        if(null==code){
            return "-1";
        }else{
            if(code.getPriceMin()<tongTien){
                return String.valueOf(tongTien-code.getGiamGia());
            }else{
                return "0";
            }
        }
    }


}
