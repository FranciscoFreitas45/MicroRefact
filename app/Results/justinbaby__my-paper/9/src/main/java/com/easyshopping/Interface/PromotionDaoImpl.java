package com.easyshopping.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.easyshopping.Interface.PromotionDao;
public class PromotionDaoImpl implements PromotionDao{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://6";


public List<Promotion> findList(Boolean hasBegun,Boolean hasEnded,Integer count,List<Filter> filters,List<Order> orders){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findList"))
    .queryParam("hasBegun",hasBegun)
    .queryParam("hasEnded",hasEnded)
    .queryParam("count",count)
    .queryParam("filters",filters)
    .queryParam("orders",orders)
;  List<Promotion> aux = restTemplate.getForObject(builder.toUriString(), List<Promotion>.class);

 return aux;
}


public Object clear(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/clear"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object flush(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/flush"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}