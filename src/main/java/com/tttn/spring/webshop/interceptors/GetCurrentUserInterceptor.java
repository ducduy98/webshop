package com.tttn.spring.webshop.interceptors;

import com.tttn.spring.webshop.Crud.*;
import com.tttn.spring.webshop.model.CartEntity;
import com.tttn.spring.webshop.model.RoleEntity;
import com.tttn.spring.webshop.model.TaikhoanEntity;
import com.tttn.spring.webshop.service.LoggerExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class GetCurrentUserInterceptor implements HandlerInterceptor {

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

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String id = null;
        if (principal instanceof UserDetails) {
            id = ((UserDetails) principal).getUsername();
        }
        if (null != id) {
            request.setAttribute("user", 1);
            TaikhoanEntity taikhoanEntity = taikhoanCrud.findById(id).orElse(null);
            List<RoleEntity> listRole = taikhoanEntity.getTaikhoanRolesById().stream().map(c -> c.getRoleByRole()).filter(c -> c.getIdrole() == 1 || c.getIdrole() == 3 || c.getIdrole() == 4).collect(Collectors.toList());
            if (listRole.size() > 0) {
                request.setAttribute("admin", 1);
            } else {
                request.setAttribute("khach", 1);
            }

            request.setAttribute("account", taikhoanEntity);
        }
        return true;
    }
}
