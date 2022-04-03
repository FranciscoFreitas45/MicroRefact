package com.easyshopping.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class StaticServiceController {

 private StaticService staticservice;


@GetMapping
("/build")
public int build(@RequestParam(name = "product") Product product){
  return staticservice.build(product);
}


@GetMapping
("/buildIndex")
public int buildIndex(){
  return staticservice.buildIndex();
}


@GetMapping
("/buildOther")
public int buildOther(){
  return staticservice.buildOther();
}


@GetMapping
("/delete")
public int delete(@RequestParam(name = "product") Product product){
  return staticservice.delete(product);
}


}