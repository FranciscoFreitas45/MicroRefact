package org.danyuan.application.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SysUserWorkExpreienceServiceController {

 private SysUserWorkExpreienceService sysuserworkexpreienceservice;


@GetMapping
("/findAll")
public Object findAll(@RequestParam(name = "Object") Object Object){
  return sysuserworkexpreienceservice.findAll(Object);
}


}