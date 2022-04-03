package com.gs.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ChargeBillServiceController {

 private ChargeBillService chargebillservice;


@GetMapping
("/queryById")
public Object queryById(@RequestParam(name = "Object") Object Object){
  return chargebillservice.queryById(Object);
}


}