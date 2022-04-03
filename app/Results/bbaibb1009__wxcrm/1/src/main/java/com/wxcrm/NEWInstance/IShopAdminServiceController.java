package com.wxcrm.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class IShopAdminServiceController {

 private IShopAdminService ishopadminservice;


@GetMapping
("/getShopAdminById")
public WcShopAdmin getShopAdminById(@RequestParam(name = "id") Integer id){
  return ishopadminservice.getShopAdminById(id);
}


}