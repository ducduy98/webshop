package com.tttn.spring.webshop.Controller;

import com.tttn.spring.webshop.Crud.ColorCrud;
import com.tttn.spring.webshop.Crud.SizeCrud;
import com.tttn.spring.webshop.domain.ProductSCCustom;
import com.tttn.spring.webshop.exception.SaveExceptionJPA;
import com.tttn.spring.webshop.model.ColorEntity;
import com.tttn.spring.webshop.model.ProductSizeColorEntity;
import com.tttn.spring.webshop.model.SizeEntity;
import com.tttn.spring.webshop.service.PhieuNhapService;
import com.tttn.spring.webshop.service.ProductService;
import com.tttn.spring.webshop.service.SearchService;
import com.tttn.spring.webshop.service.ServiceRestfull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/apinhaphang")
public class ApiNhapHang {
    @Autowired
    SizeCrud sizeCrud;

    @Autowired
    SearchService searchService;

    @Autowired
    PhieuNhapService phieuNhapService;


    @PostMapping("/themtam")
    public ResponseEntity<ServiceRestfull> addTam(@Valid @RequestBody ProductSCCustom psc) throws SaveExceptionJPA {
        return new ResponseEntity<ServiceRestfull>(phieuNhapService.add(psc), HttpStatus.OK);
    }

    @GetMapping("/search/{ten}")
    public ResponseEntity<ServiceRestfull> search(@PathVariable("ten") String ten) {
        System.out.println("ten tim kiem :" + ten);
        return new ResponseEntity<ServiceRestfull>(searchService.search(ten), HttpStatus.OK);
    }

    @GetMapping("/color")
    public ResponseEntity<ServiceRestfull> getColor() {
        return new ResponseEntity<ServiceRestfull>(searchService.color(), HttpStatus.OK);
    }

    @GetMapping("/cancel/{nhapProduct}/{maphieu}")
    public ResponseEntity<ServiceRestfull> cancel(@PathVariable("nhapProduct") int nhapProduct
            , @PathVariable("maphieu") String maphieu) {
        return new ResponseEntity<ServiceRestfull>(phieuNhapService.cancel(nhapProduct, maphieu),HttpStatus.OK);
    }
}
