package org.danyuan.application.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SysUserLanguageServiceController {

 private SysUserLanguageService sysuserlanguageservice;


@GetMapping
("/findOne")
public Object findOne(@RequestParam(name = "Object") Object Object){
  return sysuserlanguageservice.findOne(Object);
}


}