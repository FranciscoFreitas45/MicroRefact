package com.wxcrm.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class IMemcachedServiceController {

 private IMemcachedService imemcachedservice;


@GetMapping
("/getAdminNameAll")
public List<Map<String,Object>> getAdminNameAll(){
  return imemcachedservice.getAdminNameAll();
}


}