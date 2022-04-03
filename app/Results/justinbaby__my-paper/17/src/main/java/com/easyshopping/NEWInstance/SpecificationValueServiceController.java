package com.easyshopping.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SpecificationValueServiceController {

 private SpecificationValueService specificationvalueservice;


@GetMapping
("/find")
public Object find(@RequestParam(name = "Object") Object Object){
  return specificationvalueservice.find(Object);
}


}