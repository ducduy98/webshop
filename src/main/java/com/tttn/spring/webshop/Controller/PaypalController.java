package com.tttn.spring.webshop.Controller;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import com.tttn.spring.webshop.domain.Order;
import com.tttn.spring.webshop.service.PaypalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PaypalController {
    @Autowired
    PaypalService service;
    //    @GetMapping()
//    public String home(){
//
//    }
    public static final String SUCCESS_URL = "pay/success";
    public static final String CANCEL_URL = "pay/cancel";

    @PostMapping("/pay")
    public String payment(Order order) {
        try {
            Payment payment = service.createPayment(order.getPrice(), order.getCurrency(), order.getMethod(), order.getIntent(), order.getDescription(), "http://localhost:8080" + CANCEL_URL, "http://localhost:8080" + SUCCESS_URL);
            for (Links link : payment.getLinks()) {
                if (link.getRel().equals("approval_url")) {
                    System.out.println(link.getHref());
                    return "redirect:" + link.getHref();
                }
            }
        } catch (PayPalRESTException e) {
            e.printStackTrace();
        }
        return "redirect:/trangchu";
    }

    @GetMapping(value = CANCEL_URL)
    public String cancelPay(){
        return "cancel";
    }
    @GetMapping(value = SUCCESS_URL)
    public String successPay(@RequestParam("paymentId")String paymentId,@RequestParam("PayerID")String payerId){
        try {
            Payment payment=service.executePayment(paymentId,payerId);
            if (payment.getState().equals("approved")){
                return "succcess";
            }
        } catch (PayPalRESTException e) {
            System.out.println(e.getMessage());
        }
        return "redirect:/trangchu";
    }

}