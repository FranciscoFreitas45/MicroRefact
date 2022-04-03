package com.easyshopping.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.easyshopping.Interface.PromotionService;
public class PromotionServiceImpl implements PromotionService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://6";


public List<Promotion> findList(Boolean hasBegun,Boolean hasEnded,Integer count,List<Filter> filters,List<Order> orders,String cacheRegion){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findList"))
    .queryParam("hasBegun",hasBegun)
    .queryParam("hasEnded",hasEnded)
    .queryParam("count",count)
    .queryParam("filters",filters)
    .queryParam("orders",orders)
    .queryParam("cacheRegion",cacheRegion)
;  List<Promotion> aux = restTemplate.getForObject(builder.toUriString(), List<Promotion>.class);

 return aux;
}


}