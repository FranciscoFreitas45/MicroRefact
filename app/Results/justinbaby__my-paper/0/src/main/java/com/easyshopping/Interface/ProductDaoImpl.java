package com.easyshopping.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.easyshopping.Interface.ProductDao;
public class ProductDaoImpl implements ProductDao{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://7";


public Object lock(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/lock"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object merge(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/merge"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object flush(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/flush"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}