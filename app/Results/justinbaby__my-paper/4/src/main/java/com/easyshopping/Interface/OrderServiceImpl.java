package com.easyshopping.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.easyshopping.Interface.OrderService;
public class OrderServiceImpl implements OrderService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public Long waitingPaymentCount(Member member){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/waitingPaymentCount"))
    .queryParam("member",member)
;  Long aux = restTemplate.getForObject(builder.toUriString(), Long.class);

 return aux;
}


public Long waitingShippingCount(Member member){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/waitingShippingCount"))
    .queryParam("member",member)
;  Long aux = restTemplate.getForObject(builder.toUriString(), Long.class);

 return aux;
}


}