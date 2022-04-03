package com.easyshopping.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class PaymentServiceController {

 private PaymentService paymentservice;


@GetMapping
("/findBySn")
public Payment findBySn(@RequestParam(name = "sn") String sn){
  return paymentservice.findBySn(sn);
}


}