package org.danyuan.application.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SysUserBaseServiceController {

 private SysUserBaseService sysuserbaseservice;


@GetMapping
("/findById")
public Object findById(@RequestParam(name = "Object") Object Object){
  return sysuserbaseservice.findById(Object);
}


@GetMapping
("/save")
public SysUserBaseInfo save(@RequestParam(name = "info") SysUserBaseInfo info){
  return sysuserbaseservice.save(info);
}


}