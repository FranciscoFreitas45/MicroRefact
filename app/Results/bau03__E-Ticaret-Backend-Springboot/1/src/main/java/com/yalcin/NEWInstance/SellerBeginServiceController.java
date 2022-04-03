package com.yalcin.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SellerBeginServiceController {

 private SellerBeginService sellerbeginservice;


@PutMapping
("/save")
public void save(@RequestParam(name = "sellerBeginForm") SellerBeginForm sellerBeginForm){
sellerbeginservice.save(sellerBeginForm);
}


}