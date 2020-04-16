package com.tttn.spring.webshop.Controller;


import com.tttn.spring.webshop.service.UploadFile;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/apiUpload")
public class ApiUpload {

    @Autowired
    UploadFile upload;

    @PostMapping("/upload")
    @ResponseBody
    public String uploadFile(@RequestParam("files") MultipartFile[] listMultipartFiles){
        List<MultipartFile> listMulti=Arrays.asList(listMultipartFiles);
        String ghepFile=listMulti.stream().map(file->file.getOriginalFilename()).filter(file ->!StringUtils.isEmpty(file))
                .collect(Collectors.joining(","));
        System.out.println("file name la :"+ghepFile);
        String file1=null;
        if(!StringUtils.isEmpty(ghepFile)){
            file1=upload.saveUpload(listMulti);
        }

        return file1;
    }
}
