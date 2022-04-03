package com.fosun.fc.projects.creepers.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ICreepersTaxEvasionDetailServiceController {

 private ICreepersTaxEvasionDetailService icreeperstaxevasiondetailservice;


@GetMapping
("/findListByName")
public List<TCreepersTaxEvasionDetail> findListByName(@RequestParam(name = "name") String name){
  return icreeperstaxevasiondetailservice.findListByName(name);
}


}