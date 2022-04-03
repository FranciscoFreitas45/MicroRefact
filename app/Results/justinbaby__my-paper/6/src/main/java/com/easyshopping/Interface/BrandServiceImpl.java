package com.easyshopping.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.easyshopping.Interface.BrandService;
public class BrandServiceImpl implements BrandService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://7";


public Object findAll(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAll"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public List<Brand> findList(Integer count,List<Filter> filters,List<Order> orders,String cacheRegion){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findList"))
    .queryParam("count",count)
    .queryParam("filters",filters)
    .queryParam("orders",orders)
    .queryParam("cacheRegion",cacheRegion)
;  List<Brand> aux = restTemplate.getForObject(builder.toUriString(), List<Brand>.class);

 return aux;
}


}