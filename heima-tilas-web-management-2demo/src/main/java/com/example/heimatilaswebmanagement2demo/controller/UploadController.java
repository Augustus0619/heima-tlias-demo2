package com.example.heimatilaswebmanagement2demo.controller;

import com.example.heimatilaswebmanagement2demo.pojo.Result;
import com.example.heimatilaswebmanagement2demo.utils.AliOSSUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
public class UploadController
{
    @Autowired
    private AliOSSUtils aliOSSUtils;

    @PostMapping("/upload")
    public Result upload(MultipartFile image) throws Exception
    {
        String url = aliOSSUtils.upload(image);
        return Result.success(url);
    }
}
