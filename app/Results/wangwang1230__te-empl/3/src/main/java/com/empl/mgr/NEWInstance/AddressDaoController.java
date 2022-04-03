package com.empl.mgr.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class AddressDaoController {

 private AddressDao addressdao;


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return addressdao.save(Object);
}


@GetMapping
("/findUniqueByProperty")
public Object findUniqueByProperty(@RequestParam(name = "Object") Object Object){
  return addressdao.findUniqueByProperty(Object);
}


}