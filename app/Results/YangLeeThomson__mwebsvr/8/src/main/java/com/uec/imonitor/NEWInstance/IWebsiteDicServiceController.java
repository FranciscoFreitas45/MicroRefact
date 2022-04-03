package com.uec.imonitor.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class IWebsiteDicServiceController {

 private IWebsiteDicService iwebsitedicservice;


@GetMapping
("/findById")
public WebsiteDicEntity findById(@RequestParam(name = "id") int id){
  return iwebsitedicservice.findById(id);
}


}