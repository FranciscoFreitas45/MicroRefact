package com.easyshopping.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class PaymentMethodServiceController {

 private PaymentMethodService paymentmethodservice;


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return paymentmethodservice.save(Object);
}


@GetMapping
("/find")
public Object find(@RequestParam(name = "Object") Object Object){
  return paymentmethodservice.find(Object);
}


@GetMapping
("/update")
public Object update(@RequestParam(name = "Object") Object Object){
  return paymentmethodservice.update(Object);
}


@GetMapping
("/findPage")
public Object findPage(@RequestParam(name = "Object") Object Object){
  return paymentmethodservice.findPage(Object);
}


@GetMapping
("/count")
public Object count(@RequestParam(name = "Object") Object Object){
  return paymentmethodservice.count(Object);
}


@GetMapping
("/delete")
public Object delete(@RequestParam(name = "Object") Object Object){
  return paymentmethodservice.delete(Object);
}


}