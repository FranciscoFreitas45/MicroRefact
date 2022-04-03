package org.danyuan.application.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SysUserEvaluateServiceController {

 private SysUserEvaluateService sysuserevaluateservice;


@GetMapping
("/findOne")
public Object findOne(@RequestParam(name = "Object") Object Object){
  return sysuserevaluateservice.findOne(Object);
}


}