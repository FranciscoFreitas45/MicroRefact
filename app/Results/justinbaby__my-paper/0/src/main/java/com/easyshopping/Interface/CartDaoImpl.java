package com.easyshopping.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.easyshopping.Interface.CartDao;
public class CartDaoImpl implements CartDao{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://6";


public Object remove(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/remove"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}