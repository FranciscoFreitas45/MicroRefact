package com.easyshopping.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.easyshopping.Interface.ProductService;
public class ProductServiceImpl implements ProductService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://7";


public Product findBySn(String sn){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findBySn"))
    .queryParam("sn",sn)
;  Product aux = restTemplate.getForObject(builder.toUriString(), Product.class);

 return aux;
}


}