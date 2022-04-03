package com.qidian.hcm.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SocialSecurityPlanRepositoryController {

 private SocialSecurityPlanRepository socialsecurityplanrepository;


@GetMapping
("/findById")
public Object findById(@RequestParam(name = "Object") Object Object){
  return socialsecurityplanrepository.findById(Object);
}


}