package com.easyshopping.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class FileServiceController {

 private FileService fileservice;


@GetMapping
("/isValid")
public boolean isValid(@RequestParam(name = "fileType") FileType fileType,@RequestParam(name = "multipartFile") MultipartFile multipartFile){
  return fileservice.isValid(fileType,multipartFile);
}


@GetMapping
("/uploadLocal")
public String uploadLocal(@RequestParam(name = "fileType") FileType fileType,@RequestParam(name = "multipartFile") MultipartFile multipartFile){
  return fileservice.uploadLocal(fileType,multipartFile);
}


}