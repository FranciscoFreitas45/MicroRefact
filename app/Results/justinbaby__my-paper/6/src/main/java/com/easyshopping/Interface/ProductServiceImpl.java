package com.easyshopping.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.easyshopping.Interface.ProductService;
public class ProductServiceImpl implements ProductService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://7";


public List<Product> search(String keyword,Boolean isGift,Integer count){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/search"))
    .queryParam("keyword",keyword)
    .queryParam("isGift",isGift)
    .queryParam("count",count)
;  List<Product> aux = restTemplate.getForObject(builder.toUriString(), List<Product>.class);

 return aux;
}


public List<Product> findList(ProductCategory productCategory,Date beginDate,Date endDate,Integer first,Integer count){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findList"))
    .queryParam("productCategory",productCategory)
    .queryParam("beginDate",beginDate)
    .queryParam("endDate",endDate)
    .queryParam("first",first)
    .queryParam("count",count)
;  List<Product> aux = restTemplate.getForObject(builder.toUriString(), List<Product>.class);

 return aux;
}


public Object find(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/find"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}