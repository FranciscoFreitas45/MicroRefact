package com.zammc.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class GoodsCateServiceController {

 private GoodsCateService goodscateservice;


@GetMapping
("/queryCateList")
public List<GoodsCateEntity> queryCateList(){
  return goodscateservice.queryCateList();
}


}