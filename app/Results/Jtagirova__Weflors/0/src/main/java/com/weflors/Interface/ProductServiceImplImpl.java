package com.weflors.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.weflors.Interface.ProductServiceImpl;
public class ProductServiceImplImpl implements ProductServiceImpl{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public ProductEntity saveProduct(ProductEntity productEntity){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/saveProduct"))
    .queryParam("productEntity",productEntity)
;  ProductEntity aux = restTemplate.getForObject(builder.toUriString(), ProductEntity.class);

 return aux;
}


}