package org.danyuan.application.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SysUserEducationServiceController {

 private SysUserEducationService sysusereducationservice;


@GetMapping
("/findAll")
public Object findAll(@RequestParam(name = "Object") Object Object){
  return sysusereducationservice.findAll(Object);
}


}