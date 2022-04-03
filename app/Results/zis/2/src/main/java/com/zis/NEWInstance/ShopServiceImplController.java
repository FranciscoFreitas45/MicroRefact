package com.zis.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ShopServiceImplController {

 private ShopServiceImpl shopserviceimpl;


@GetMapping
("/findCompanyShop")
public List<ShopInfo> findCompanyShop(@RequestParam(name = "companyId") Integer companyId){
  return shopserviceimpl.findCompanyShop(companyId);
}


}