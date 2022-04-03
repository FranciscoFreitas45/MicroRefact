package com.easyshopping.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class BrandDaoController {

 private BrandDao branddao;


@GetMapping
("/findList")
public Object findList(@RequestParam(name = "Object") Object Object){
  return branddao.findList(Object);
}


}