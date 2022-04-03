package com.zis.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ShopServiceController {

 private ShopService shopservice;


@GetMapping
("/queryCompany")
public Page<Company> queryCompany(@RequestParam(name = "companyName") String companyName,@RequestParam(name = "contacts") String contacts,@RequestParam(name = "page") Pageable page){
  return shopservice.queryCompany(companyName,contacts,page);
}


@GetMapping
("/findCompanyOne")
public Company findCompanyOne(@RequestParam(name = "companyId") Integer companyId){
  return shopservice.findCompanyOne(companyId);
}


@PutMapping
("/updateCompany")
public void updateCompany(@RequestParam(name = "dto") SaveOrUpdateCompanyDto dto){
shopservice.updateCompany(dto);
}


@PutMapping
("/saveCompany")
public void saveCompany(@RequestParam(name = "dto") SaveOrUpdateCompanyDto dto){
shopservice.saveCompany(dto);
}


@GetMapping
("/findCompanyShop")
public List<ShopInfo> findCompanyShop(@RequestParam(name = "companyId") Integer companyId){
  return shopservice.findCompanyShop(companyId);
}


@GetMapping
("/queryAllCompany")
public List<Company> queryAllCompany(){
  return shopservice.queryAllCompany();
}


@PutMapping
("/stockChangeToShopUPdateItem")
public void stockChangeToShopUPdateItem(@RequestParam(name = "companyId") Integer companyId,@RequestParam(name = "bookId") Integer bookId,@RequestParam(name = "amount") Integer amount){
shopservice.stockChangeToShopUPdateItem(companyId,bookId,amount);
}


@PutMapping
("/logisticsOfflineSend")
public void logisticsOfflineSend(@RequestParam(name = "list") List<ExpressNumberDTO> list){
shopservice.logisticsOfflineSend(list);
}


@GetMapping
("/findShopByShopIdAndCompanyId")
public ShopInfo findShopByShopIdAndCompanyId(@RequestParam(name = "companyId") Integer companyId,@RequestParam(name = "shopId") Integer shopId){
  return shopservice.findShopByShopIdAndCompanyId(companyId,shopId);
}


@GetMapping
("/findShopById")
public ShopInfo findShopById(@RequestParam(name = "shopId") Integer shopId){
  return shopservice.findShopById(shopId);
}


}