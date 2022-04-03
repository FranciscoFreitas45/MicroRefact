package com.easyshopping.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.easyshopping.Interface.ProductImageService;
public class ProductImageServiceImpl implements ProductImageService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


public void build(ProductImage productImage){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/build"))
    .queryParam("productImage",productImage)
;
  restTemplate.put(builder.toUriString(), null);
}


}