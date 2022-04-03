package com.lingxiang2014.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class BSystemAccountServiceController {

 private BSystemAccountService bsystemaccountservice;


@GetMapping
("/findLeaf")
public BSystemAccount findLeaf(@RequestParam(name = "index") Integer index){
  return bsystemaccountservice.findLeaf(index);
}


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return bsystemaccountservice.save(Object);
}


@GetMapping
("/update")
public Object update(@RequestParam(name = "Object") Object Object){
  return bsystemaccountservice.update(Object);
}


}