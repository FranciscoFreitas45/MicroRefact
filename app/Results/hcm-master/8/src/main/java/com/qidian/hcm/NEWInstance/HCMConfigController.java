package com.qidian.hcm.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class HCMConfigController {

 private HCMConfig hcmconfig;


@GetMapping
("/getLoginVerificationCodeEffectiveTime")
public Object getLoginVerificationCodeEffectiveTime(@RequestParam(name = "Object") Object Object){
  return hcmconfig.getLoginVerificationCodeEffectiveTime(Object);
}


@GetMapping
("/getTenantUrl")
public Object getTenantUrl(@RequestParam(name = "Object") Object Object){
  return hcmconfig.getTenantUrl(Object);
}


@GetMapping
("/getMysqlConnParams")
public Object getMysqlConnParams(@RequestParam(name = "Object") Object Object){
  return hcmconfig.getMysqlConnParams(Object);
}


}