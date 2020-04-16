package com.tttn.spring.webshop.Controller;

import com.tttn.spring.webshop.dto.DashboardTotal;
import com.tttn.spring.webshop.service.PhieuMuaService;
import com.tttn.spring.webshop.service.ServiceRestfull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/dashboard/total")
public class ApiDashboardTotal {

    @Autowired
    PhieuMuaService phieuMuaService;

    @GetMapping
    public ResponseEntity<ServiceRestfull> getTotal() throws ParseException {
        return new ResponseEntity<ServiceRestfull>(phieuMuaService.dashboardTotal(), HttpStatus.OK);
    }
}
