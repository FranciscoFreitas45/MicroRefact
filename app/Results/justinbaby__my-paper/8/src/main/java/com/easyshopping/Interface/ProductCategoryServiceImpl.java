package com.easyshopping.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.easyshopping.Interface.ProductCategoryService;
public class ProductCategoryServiceImpl implements ProductCategoryService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://7";


public List<ProductCategory> findTree(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findTree"))
;  List<ProductCategory> aux = restTemplate.getForObject(builder.toUriString(), List<ProductCategory>.class);

 return aux;
}


public Object find(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/find"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}