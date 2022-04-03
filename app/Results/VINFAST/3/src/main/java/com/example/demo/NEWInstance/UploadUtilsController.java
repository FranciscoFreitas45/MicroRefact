package com.example.demo.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class UploadUtilsController {

 private UploadUtils uploadutils;


@PutMapping
("/uploadProduct")
public void uploadProduct(@RequestParam(name = "fileName") String fileName,@RequestParam(name = "multipartFile") MultipartFile multipartFile){
uploadutils.uploadProduct(fileName,multipartFile);
}


}