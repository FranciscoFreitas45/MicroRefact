package com.yalcin.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.yalcin.Interface.ProductService;
public class ProductServiceImpl implements ProductService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


public List<Product> getUserProduct(String userId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getUserProduct"))
    .queryParam("userId",userId)
;  List<Product> aux = restTemplate.getForObject(builder.toUriString(), List<Product>.class);

 return aux;
}


}