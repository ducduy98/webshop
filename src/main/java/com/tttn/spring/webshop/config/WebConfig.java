package com.tttn.spring.webshop.config;

import com.tttn.spring.webshop.interceptors.GetCurrentUserInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    GetCurrentUserInterceptor getCurrentUserInterceptor;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/product/**")
                .addResourceLocations("file:D:\\TTTN\\webshop\\src\\main\\resources\\static\\assets\\img\\product\\");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getCurrentUserInterceptor);
    }

}
