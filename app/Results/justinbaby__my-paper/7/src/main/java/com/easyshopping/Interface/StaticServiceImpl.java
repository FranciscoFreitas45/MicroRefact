package com.easyshopping.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.easyshopping.Interface.StaticService;
public class StaticServiceImpl implements StaticService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://9";


public int build(Product product){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/build"))
    .queryParam("product",product)
;  int aux = restTemplate.getForObject(builder.toUriString(), int.class);

 return aux;
}


public int delete(Product product){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/delete"))
    .queryParam("product",product)
;  int aux = restTemplate.getForObject(builder.toUriString(), int.class);

 return aux;
}


}