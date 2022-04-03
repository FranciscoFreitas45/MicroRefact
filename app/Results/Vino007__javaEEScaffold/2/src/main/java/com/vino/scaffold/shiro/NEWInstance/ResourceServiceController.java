package com.vino.scaffold.shiro.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ResourceServiceController {

 private ResourceService resourceservice;


@GetMapping
("/findAll")
public Object findAll(@RequestParam(name = "Object") Object Object){
  return resourceservice.findAll(Object);
}


}