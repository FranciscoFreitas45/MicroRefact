package com.example.steam.controller;
 import com.alibaba.fastjson.JSON;
import com.example.steam.utils.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
@Controller
public class FileController {

@Autowired
 private FileUploadUtil fileUploadUtil;


@ResponseBody
@RequestMapping("/file/upload")
public String upload(MultipartFile file){
    return JSON.toJSONString(fileUploadUtil.handleFileUpload(file));
}


@ResponseBody
@RequestMapping("/file/multipleattributesupload")
public String uploadImages(HttpServletRequest request){
    return JSON.toJSONString(fileUploadUtil.handleMultipleAttributrUpload(request));
}


}