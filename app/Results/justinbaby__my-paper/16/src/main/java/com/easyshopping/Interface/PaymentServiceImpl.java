package com.easyshopping.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.easyshopping.Interface.PaymentService;
public class PaymentServiceImpl implements PaymentService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public Payment findBySn(String sn){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findBySn"))
    .queryParam("sn",sn)
;  Payment aux = restTemplate.getForObject(builder.toUriString(), Payment.class);

 return aux;
}


}