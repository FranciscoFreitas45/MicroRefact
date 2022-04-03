package com.easyshopping.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.easyshopping.Interface.ProductCategoryService;
public class ProductCategoryServiceImpl implements ProductCategoryService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://7";


public List<ProductCategory> findRoots(Integer count,String cacheRegion){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findRoots"))
    .queryParam("count",count)
    .queryParam("cacheRegion",cacheRegion)
;  List<ProductCategory> aux = restTemplate.getForObject(builder.toUriString(), List<ProductCategory>.class);

 return aux;
}


}