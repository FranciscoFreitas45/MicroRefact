package com.qidian.hcm.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class FileServiceController {

 private FileService fileservice;


@GetMapping
("/uploadFileToOSS")
public Attachment uploadFileToOSS(@RequestParam(name = "base64Str") String base64Str,@RequestParam(name = "fileName") String fileName){
  return fileservice.uploadFileToOSS(base64Str,fileName);
}


@GetMapping
("/getAvatarImgUrl")
public String getAvatarImgUrl(@RequestParam(name = "fileId") Long fileId){
  return fileservice.getAvatarImgUrl(fileId);
}


}