package com.easyshopping.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SpecificationServiceController {

 private SpecificationService specificationservice;


@GetMapping
("/findAll")
public Object findAll(@RequestParam(name = "Object") Object Object){
  return specificationservice.findAll(Object);
}


@GetMapping
("/find")
public Object find(@RequestParam(name = "Object") Object Object){
  return specificationservice.find(Object);
}


}