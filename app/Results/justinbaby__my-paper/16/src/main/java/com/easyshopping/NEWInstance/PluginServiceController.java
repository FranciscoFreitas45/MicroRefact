package com.easyshopping.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class PluginServiceController {

 private PluginService pluginservice;


@GetMapping
("/getPaymentPlugins")
public List<PaymentPlugin> getPaymentPlugins(@RequestParam(name = "isEnabled") boolean isEnabled){
  return pluginservice.getPaymentPlugins(isEnabled);
}


@GetMapping
("/getPaymentPlugin")
public PaymentPlugin getPaymentPlugin(@RequestParam(name = "id") String id){
  return pluginservice.getPaymentPlugin(id);
}


}