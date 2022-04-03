package com.byr.warehouse.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class StockHUBController {

 private StockHUB stockhub;

 private StockHUB stockhub;


@PutMapping
("/setGoodNum")
public void setGoodNum(@RequestParam(name = "goodNum") String goodNum){
stockhub.setGoodNum(goodNum);
}


@PutMapping
("/setStorageDate")
public void setStorageDate(@RequestParam(name = "storageDate") String storageDate){
stockhub.setStorageDate(storageDate);
}


@PutMapping
("/setSupplyMateriCode")
public void setSupplyMateriCode(@RequestParam(name = "supplyMateriCode") String supplyMateriCode){
stockhub.setSupplyMateriCode(supplyMateriCode);
}


@PutMapping
("/setProdate")
public void setProdate(@RequestParam(name = "prodate") String prodate){
stockhub.setProdate(prodate);
}


@PutMapping
("/setWeixinCode")
public void setWeixinCode(@RequestParam(name = "weixinCode") String weixinCode){
stockhub.setWeixinCode(weixinCode);
}


@PutMapping
("/setVendorCode")
public void setVendorCode(@RequestParam(name = "vendorCode") String vendorCode){
stockhub.setVendorCode(vendorCode);
}


}