package com.easyshopping.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class PaymentPluginController {

 private PaymentPlugin paymentplugin;

 private PaymentPlugin paymentplugin;


@GetMapping
("/calculateFee")
public BigDecimal calculateFee(@RequestParam(name = "amount") BigDecimal amount){
  return paymentplugin.calculateFee(amount);
}


@GetMapping
("/calculateAmount")
public BigDecimal calculateAmount(@RequestParam(name = "amount") BigDecimal amount){
  return paymentplugin.calculateAmount(amount);
}


@GetMapping
("/verifyNotify")
public boolean verifyNotify(@RequestParam(name = "sn") String sn,@RequestParam(name = "notifyMethod") NotifyMethod notifyMethod,@RequestParam(name = "request") HttpServletRequest request){
  return paymentplugin.verifyNotify(sn,notifyMethod,request);
}


}