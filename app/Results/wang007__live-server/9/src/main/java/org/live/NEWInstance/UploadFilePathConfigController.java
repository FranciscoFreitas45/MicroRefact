package org.live.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class UploadFilePathConfigController {

 private UploadFilePathConfig uploadfilepathconfig;


@GetMapping
("/getUploadFilePath")
public String getUploadFilePath(){
  return uploadfilepathconfig.getUploadFilePath();
}


@GetMapping
("/getUploadFilePathPrefix")
public String getUploadFilePathPrefix(){
  return uploadfilepathconfig.getUploadFilePathPrefix();
}


@GetMapping
("/getUploadFileRootPath")
public String getUploadFileRootPath(){
  return uploadfilepathconfig.getUploadFileRootPath();
}


}