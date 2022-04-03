package com.yalcin.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.yalcin.Interface.ProductService;
public class ProductServiceImpl implements ProductService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


public Product getProduct(String productId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getProduct"))
    .queryParam("productId",productId)
;  Product aux = restTemplate.getForObject(builder.toUriString(), Product.class);

 return aux;
}


public void productEdit(String productId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/productEdit"))
    .queryParam("productId",productId)
;
  restTemplate.put(builder.toUriString(), null);
}


}