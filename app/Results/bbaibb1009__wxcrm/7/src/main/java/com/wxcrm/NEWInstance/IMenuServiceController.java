package com.wxcrm.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class IMenuServiceController {

 private IMenuService imenuservice;


@GetMapping
("/queryMenuToCache")
public List<Map<String,Object>> queryMenuToCache(){
  return imenuservice.queryMenuToCache();
}


@GetMapping
("/queryShopMenuToCache")
public List<Map<String,Object>> queryShopMenuToCache(){
  return imenuservice.queryShopMenuToCache();
}


}