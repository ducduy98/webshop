package com.tttn.spring.webshop.Controller;

import com.tttn.spring.webshop.Crud.*;
import com.tttn.spring.webshop.model.*;
import com.tttn.spring.webshop.service.LoggerExample;
import org.omg.IOP.Codec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.nio.file.Files;
import java.security.Principal;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller

@SessionAttributes("myCart")
public class MuaHangController {

    @Autowired
    CartCrud cartCrud;

    @Autowired
    CodeCrud codeCrud;

    @Autowired
    TaikhoanCrud taikhoanCrud;

    @Autowired
    PhieuMuaCrud phieuMuaCrud;

    @Autowired
    ProductSizeColorCrud productSizeColorCrud;

    @Autowired
    PhieuMuaCartCrud phieuMuaCartCrud;

    @Autowired
    ChiTietCrud chiTietCrud;

    @Autowired
    ProductCrud productCrud;



    @RequestMapping(value = "/muaHang",method = RequestMethod.GET)
    public String muaHang(@RequestParam("check1") List<Integer> listCheck,

                          @RequestParam("sale") String sale, Model model){

       // listCheck.stream().forEach(System.out::println);

        Object principal= SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String id=null;
        if(principal instanceof UserDetails){
            id=((UserDetails) principal).getUsername();
        }
        if(id!=null){

            TaikhoanEntity taikhoanEntity=taikhoanCrud.findById(id).orElse(null);
            if(taikhoanEntity.getChitietsById().size()>0){
                List<ChitietEntity> chitiets= (List<ChitietEntity>) taikhoanEntity.getChitietsById();
                model.addAttribute("chitiet",chitiets);
                model.addAttribute("sl",1);
            }else{
                model.addAttribute("sl",0);
            }
            model.addAttribute("taikhoan",1);

            CodeEntity code=codeCrud.codeExist(sale);
            List<CartEntity> listCart=new ArrayList<>();
            listCheck.stream().forEach(c->{
                CartEntity cartEntity=cartCrud.findById(c).orElse(null);
                if(null!=cartEntity) {
                    listCart.add(cartEntity);
                    System.out.println("soluong :"+cartEntity.getSoluong());
                }

            });
            double tong=listCart.stream().mapToDouble(c->c.thanhTien()).sum();
            int giamGia=0;
            int maCode=0;
            if(null==code){
                model.addAttribute("sale",0);
            }else{
                if(tong>code.getPriceMin()){
                    giamGia=code.getGiamGia();
                    model.addAttribute("sale",giamGia);
                    maCode=code.getIdcode();

                }else{
                    model.addAttribute("sale",0);
                }
            }
            StringBuffer maPhieu=new StringBuffer("PM");
            Random random=new Random();
            boolean exist=true;
            int i=0;
            while(exist){
                i=random.nextInt(1_000_000_000);

                if(null==phieuMuaCrud.findById("PM"+i).orElse(null)){
                    exist=false;
                    maPhieu.append(String.valueOf(i));
                }
            }
            model.addAttribute("maPhieu",maPhieu);
            model.addAttribute("tongAll",tong);
            model.addAttribute("dsProduct",listCart);
            model.addAttribute("maCode",maCode);


        }else {
            model.addAttribute("taikhoan",0);
            CodeEntity code=codeCrud.codeExist(sale);
            List<CartEntity> listCart=new ArrayList<>();
            listCheck.stream().forEach(c->{
                    CartEntity cartEntity=cartCrud.findById(c).orElse(null);
                    if(null!=cartEntity) {
                        listCart.add(cartEntity);
                        System.out.println("soluong :"+cartEntity.getSoluong());
                    }

            });
            double tong=listCart.stream().mapToDouble(c->c.thanhTien()).sum();
            int giamGia=0;
            int maCode=0;
            if(null==code){
                model.addAttribute("sale",0);
            }else{
                if(tong>code.getPriceMin()){
                    giamGia=code.getGiamGia();
                    model.addAttribute("sale",giamGia);
                    maCode=code.getIdcode();

                }else{
                    model.addAttribute("sale",0);
                }
            }
            StringBuffer maPhieu=new StringBuffer("PM");
            Random random=new Random();
            boolean exist=true;
            int i=0;
            while(exist){
                i=random.nextInt(1_000_000_000);

                if(null==phieuMuaCrud.findById("PM"+i).orElse(null)){
                    exist=false;
                    maPhieu.append(String.valueOf(i));
                }
            }
            model.addAttribute("maPhieu",maPhieu);
            model.addAttribute("tongAll",tong);
            model.addAttribute("dsProduct",listCart);
            model.addAttribute("maCode",maCode);
        }



        return "muaHang";
    }

    @RequestMapping(value = "/muahang/hoanthanh",method = RequestMethod.POST)
    public String hoanThanh(@RequestParam("maPhieu") String maPhieu, @RequestParam("idCart") List<Integer> listIdCarts,
                            @RequestParam("idDiaChi") int idDiaChi, @RequestParam("idCode") int maCode, Model model, HttpServletRequest request){

        if(checkCart(listIdCarts).size()==listIdCarts.size()){
            ChitietEntity chitietEntity=chiTietCrud.findById(idDiaChi).orElse(null);
            Date ngay= Calendar.getInstance().getTime();
            Timestamp time=new Timestamp(ngay.getTime());
            if(null==chitietEntity){
                model.addAttribute("result","bạn chưa có thông tin địa chỉ để giao hàng");
            }
            else if(!validateDiaChi(chitietEntity)){
                model.addAttribute("result","bạn để trống 1 vài ô hoặc không đầy đủ thông tin địa chỉ nên không thể mua hàng");
            }
            else {

                Object principal= SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                String id=null;
                if(principal instanceof UserDetails){
                    id=((UserDetails) principal).getUsername();
                }
                if(id!=null){
                    String finalId = id;

                    for(Integer idCart:listIdCarts)
                    {
                        CartEntity cartEntity = cartCrud.findById(idCart).orElse(null);
                        PhieumuaCartEntity phieumuaCartEntity = new PhieumuaCartEntity();
                        PhieumuaEntity phieumuaEntity = new PhieumuaEntity();

                        if(maCode!=0){
                            phieumuaEntity.setMaPhieu(maPhieu).setStatus(1).setChitietEntity(chitietEntity).setDate(time).setCodeByCode(codeCrud.findById(maCode).orElse(null))
                                    .setTaikhoanByTaikhoan(taikhoanCrud.findById(finalId).orElse(null));
                            phieuMuaCrud.save(phieumuaEntity);
                        }
                        else{
                            phieumuaEntity.setMaPhieu(maPhieu).setStatus(1).setChitietEntity(chitietEntity).setDate(time)
                                    .setTaikhoanByTaikhoan(taikhoanCrud.findById(finalId).orElse(null));
                            phieuMuaCrud.save(phieumuaEntity);
                        }
                        cartEntity.setStatus(2);
                        cartCrud.save(cartEntity);
                        ProductSizeColorEntity productSizeColorEntity=cartEntity.getProductSizeColorEntity();
                        productSizeColorEntity.setNumber(productSizeColorEntity.getNumber()-cartEntity.getSoluong());
                        productSizeColorCrud.save(productSizeColorEntity);

                        // them cau update product
                        ProductEntity productEntity=productSizeColorEntity.getProductByProduct();
                        productEntity.setSold(productEntity.getSold()+cartEntity.getSoluong());
                        productCrud.save(productEntity);

                        // ket thuc
                        phieumuaCartEntity.setCartByCart(cartEntity);
                        phieumuaCartEntity.setPhieumuaByPhieumua(phieumuaEntity);
                        phieumuaCartEntity.setIdphieumuaCart(randomId());
                        phieuMuaCartCrud.save(phieumuaCartEntity);

                    }

                    model.addAttribute("result","hoàn thành mua hàng");

                }else{
                    listIdCarts.stream().forEach(idCart -> {
                        CartEntity cartEntity = cartCrud.findById(idCart).orElse(null);
                        PhieumuaCartEntity phieumuaCartEntity = new PhieumuaCartEntity();
                        PhieumuaEntity phieumuaEntity = new PhieumuaEntity();

                        if(maCode!=0){
                            phieumuaEntity.setMaPhieu(maPhieu).setStatus(1).setChitietEntity(chitietEntity).setCodeByCode(codeCrud.findById(maCode).orElse(null))
                                    .setDate(time);
                            phieuMuaCrud.save(phieumuaEntity);
                        }
                        else{
                            phieumuaEntity.setMaPhieu(maPhieu).setStatus(1).setChitietEntity(chitietEntity)
                                    .setDate(time);
                            phieuMuaCrud.save(phieumuaEntity);
                        }
                        HttpSession httpSession=request.getSession();
                        List<CartEntity> myCart= (List<CartEntity>) httpSession.getAttribute("myCart");
                        System.out.println("so luong gio  hang la :"+myCart.size());
                        List<CartEntity> myCart2=myCart.stream().filter(c->c.getIdcart()!=idCart).collect(Collectors.toList());
                        model.addAttribute("myCart",myCart2);
                        ProductSizeColorEntity productSizeColorEntity=cartEntity.getProductSizeColorEntity();
                        productSizeColorEntity.setNumber(productSizeColorEntity.getNumber()-cartEntity.getSoluong());
                        productSizeColorCrud.save(productSizeColorEntity);

                        // cau  lenh update product them so luong ban
                        ProductEntity productEntity=productSizeColorEntity.getProductByProduct();
                        productEntity.setSold(productEntity.getSold()+cartEntity.getSoluong());
                        productCrud.save(productEntity);

                        // ket thuc
                        phieumuaCartEntity.setCartByCart(cartEntity);
                        phieumuaCartEntity.setPhieumuaByPhieumua(phieumuaEntity);
                        phieumuaCartEntity.setIdphieumuaCart(randomId());
                        phieuMuaCartCrud.save(phieumuaCartEntity);

                    });

                    model.addAttribute("result","hoàn thành mua hàng");
                }

            }
        }else{
            model.addAttribute("result","1 trong những sản phẩm bạn mua đã có người nhanh tay mua trước và lượng hàng đã không đủ cung cấp ");
        }
        return "hoanthanh";
    }

    public List<Integer> checkCart(List<Integer> idCart){
        List<Integer> listCheckIdCart=idCart.stream().filter(id->{
            CartEntity cart =cartCrud.findById(id).orElse(null);
            if(cart.getSoluong()<=cart.getProductSizeColorEntity().getNumber()){
                return true;
            }else{
                return false;
            }
        }).collect(Collectors.toList());

        return listCheckIdCart;
    }
    public boolean validateDiaChi(ChitietEntity chitietEntity){
        if(null== chitietEntity.getTen() || null== chitietEntity.getSdt() || null== chitietEntity.getChitiet() || null== chitietEntity.getXa()
        || null== chitietEntity.getHuyen() || null== chitietEntity.getThanhpho()){
            return false;
        }
        else {
            return true;
        }
    }
    public int randomId(){
        Random random=new Random();
        boolean exist=true;
        int i=0;
        while(exist){
            i=random.nextInt(1_000_000_000)+1;

            if(null==phieuMuaCartCrud.findById(i).orElse(null)){
                exist=false;
            }
        }
        return i;
    }
}
