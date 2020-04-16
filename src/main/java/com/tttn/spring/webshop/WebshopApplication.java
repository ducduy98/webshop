package com.tttn.spring.webshop;

import com.tttn.spring.webshop.service.UploadFile;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;

@SpringBootApplication
public class WebshopApplication {

    public static void main(String[] args) {
        new File(UploadFile.uploadingDir).mkdirs();
        SpringApplication.run(WebshopApplication.class, args);
    }

}
