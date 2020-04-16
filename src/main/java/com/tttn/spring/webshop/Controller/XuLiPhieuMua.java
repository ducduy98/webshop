package com.tttn.spring.webshop.Controller;

import com.tttn.spring.webshop.Crud.PhieuMuaCrud;
import com.tttn.spring.webshop.model.PhieumuaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/donhang")
public class XuLiPhieuMua {
    @Autowired
    PhieuMuaCrud phieuMuaCrud;

    @GetMapping
    public String donHang(Model model){
        Pageable pageable= PageRequest.of(0,10, Sort.by("date").descending());
        Page<PhieumuaEntity> phieumuaEntities=phieuMuaCrud.getTheoStatus(1,pageable);

        return "donhang";
    }
}
