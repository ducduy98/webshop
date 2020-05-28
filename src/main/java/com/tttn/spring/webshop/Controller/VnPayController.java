package com.tttn.spring.webshop.Controller;

import com.tttn.spring.webshop.Crud.PhieuMuaCrud;
import com.tttn.spring.webshop.vnpay.Config;
import com.tttn.spring.webshop.model.PhieumuaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/vnpay")
public class VnPayController {

    @Autowired
    private PhieuMuaCrud phieuMuaCrud;

    @GetMapping
    public String order(
            @RequestParam("orderId") String maPhieu,
            Model model
    ){
        PhieumuaEntity phieumuaEntity=phieuMuaCrud.findById(maPhieu).orElse(null);
        model.addAttribute("orderInfo",phieumuaEntity.getMaPhieu());
        model.addAttribute("txnRef",phieumuaEntity.getMaPhieu());
        model.addAttribute("amount",phieumuaEntity.thanhTien());
        model.addAttribute("customerInfo",phieumuaEntity.getChitietEntity());
        return "order";
    }

    @GetMapping("/success")
    public String success(
            @RequestParam("vnp_TxnRef") String orderId
    ){
        PhieumuaEntity phieumuaEntity=phieuMuaCrud.findById(orderId).orElse(null);
        phieumuaEntity.setStatus(4);
        phieuMuaCrud.save(phieumuaEntity);
        return "hoanthanh";
    }

    @PostMapping
    public String createOrder(HttpServletRequest req) throws UnsupportedEncodingException {
        System.out.println("create order");
        req.getParameterMap().forEach((k,v)->{
            System.out.println("param "+k+" has value "+v[0].toString());
        });
        String vnp_Version = "2.0.0";
        String vnp_Command = "pay";
        String vnp_OrderInfo = req.getParameter("vnp_OrderInfo");
        String orderType = req.getParameter("ordertype");
//        String vnp_TxnRef = Config.getRandomNumber(8);
        String vnp_TxnRef = req.getParameter("txnRef");
        String vnp_IpAddr = Config.getIpAddress(req);

        String vnp_TmnCode = Config.vnp_TmnCode;

        String vnp_TransactionNo = vnp_TxnRef;
        String vnp_hashSecret = Config.vnp_HashSecret;

        int amount = Integer.parseInt(req.getParameter("amount")) * 100;
        Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", vnp_Version);
        vnp_Params.put("vnp_Command", vnp_Command);
        vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
        vnp_Params.put("vnp_Amount", String.valueOf(amount));
        vnp_Params.put("vnp_CurrCode", "VND");
        String bank_code = req.getParameter("bankcode");
        if (bank_code != null && bank_code.isEmpty()) {
            vnp_Params.put("vnp_BankCode", bank_code);
        }
        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
        vnp_Params.put("vnp_OrderInfo", vnp_OrderInfo);
        vnp_Params.put("vnp_OrderType", orderType);

        String locate = req.getParameter("language");
        if (locate != null && !locate.isEmpty()) {
            vnp_Params.put("vnp_Locale", locate);
        } else {
            vnp_Params.put("vnp_Locale", "vn");
        }
        vnp_Params.put("vnp_ReturnUrl", Config.vnp_Returnurl);
        vnp_Params.put("vnp_IpAddr", vnp_IpAddr);

        Date dt = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String dateString = formatter.format(dt);
        String vnp_CreateDate = dateString;
        String vnp_TransDate = vnp_CreateDate;
        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

        //Build data to hash and querystring
        List fieldNames = new ArrayList(vnp_Params.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        Iterator itr = fieldNames.iterator();
        while (itr.hasNext()) {
            String fieldName = (String) itr.next();
            String fieldValue = (String) vnp_Params.get(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                //Build hash data
                hashData.append(fieldName);
                hashData.append('=');
                hashData.append(fieldValue);
                //Build query
                query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                query.append('=');
                query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));

                if (itr.hasNext()) {
                    query.append('&');
                    hashData.append('&');
                }
            }
        }
        String queryUrl = query.toString();
        String vnp_SecureHash = Config.Sha256(Config.vnp_HashSecret + hashData.toString());
        //System.out.println("HashData=" + hashData.toString());
        queryUrl += "&vnp_SecureHashType=SHA256&vnp_SecureHash=" + vnp_SecureHash;
        String paymentUrl = Config.vnp_PayUrl + "?" + queryUrl;
        System.out.println(paymentUrl);

        return "redirect:" + paymentUrl;
    }
}
