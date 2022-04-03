package com.evolvingreality.onleave.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SecurityGroupServiceController {

 private SecurityGroupService securitygroupservice;


@GetMapping
("/get")
public Object get(@RequestParam(name = "Object") Object Object){
  return securitygroupservice.get(Object);
}


}