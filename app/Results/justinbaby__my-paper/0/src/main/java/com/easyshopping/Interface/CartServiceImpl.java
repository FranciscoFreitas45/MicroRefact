package com.easyshopping.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.easyshopping.Interface.CartService;
public class CartServiceImpl implements CartService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://6";


public Cart getCurrent(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getCurrent"))
;  Cart aux = restTemplate.getForObject(builder.toUriString(), Cart.class);

 return aux;
}


}