package org.danyuan.application.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SysUserCredentialsServiceController {

 private SysUserCredentialsService sysusercredentialsservice;


@GetMapping
("/findOne")
public Object findOne(@RequestParam(name = "Object") Object Object){
  return sysusercredentialsservice.findOne(Object);
}


}