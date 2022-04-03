package com.wxcrm.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class IWebsiteServiceController {

 private IWebsiteService iwebsiteservice;


@GetMapping
("/queryMySiteByAdmin")
public WcWebsite queryMySiteByAdmin(@RequestParam(name = "wadId") Integer wadId){
  return iwebsiteservice.queryMySiteByAdmin(wadId);
}


@GetMapping
("/getWebSiteById")
public WcWebsite getWebSiteById(@RequestParam(name = "id") Integer id){
  return iwebsiteservice.getWebSiteById(id);
}


}