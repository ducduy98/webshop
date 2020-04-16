package com.tttn.spring.webshop.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UploadFile {

    public static final String uploadingDir = System.getProperty("user.dir") + "/src/main/resources/static/assets/img/product/";
    public String saveUpload(List<MultipartFile> multipartFiles){
        System.out.println("duong dan :"+uploadingDir);

        List<String> listName=new ArrayList<>();

        for(MultipartFile multipartFile:multipartFiles){
            listName.add("product/"+multipartFile.getOriginalFilename());
            File file=new File(uploadingDir+multipartFile.getOriginalFilename());
            try {
                multipartFile.transferTo(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String nameInput=listName.stream().filter(c->!StringUtils.isEmpty(c)).collect(Collectors.joining(","));


        return nameInput;


    }
}
