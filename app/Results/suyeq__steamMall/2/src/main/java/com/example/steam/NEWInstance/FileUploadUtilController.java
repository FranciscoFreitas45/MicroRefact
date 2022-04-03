package com.example.steam.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class FileUploadUtilController {

 private FileUploadUtil fileuploadutil;


@GetMapping
("/handleMultipartFile")
public String handleMultipartFile(@RequestParam(name = "file") MultipartFile file){
  return fileuploadutil.handleMultipartFile(file);
}


}