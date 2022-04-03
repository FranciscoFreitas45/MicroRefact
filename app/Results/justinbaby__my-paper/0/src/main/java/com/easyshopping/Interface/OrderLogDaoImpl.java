package com.easyshopping.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.easyshopping.Interface.OrderLogDao;
public class OrderLogDaoImpl implements OrderLogDao{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://18";


public Object persist(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/persist"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}