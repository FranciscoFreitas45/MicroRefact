package org.danyuan.application.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SysFileUploadInfoServiceController {

 private SysFileUploadInfoService sysfileuploadinfoservice;


@GetMapping
("/findOne")
public Object findOne(@RequestParam(name = "Object") Object Object){
  return sysfileuploadinfoservice.findOne(Object);
}


}