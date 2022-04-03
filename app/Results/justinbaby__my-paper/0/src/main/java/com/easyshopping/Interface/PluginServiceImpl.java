package com.easyshopping.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.easyshopping.Interface.PluginService;
public class PluginServiceImpl implements PluginService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://16";


public PaymentPlugin getPaymentPlugin(String id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getPaymentPlugin"))
    .queryParam("id",id)
;  PaymentPlugin aux = restTemplate.getForObject(builder.toUriString(), PaymentPlugin.class);

 return aux;
}


}