package com.easyshopping.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.easyshopping.Interface.TagService;
public class TagServiceImpl implements TagService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://9";


public List<Tag> findList(Integer count,List<Filter> filters,List<Order> orders,String cacheRegion){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findList"))
    .queryParam("count",count)
    .queryParam("filters",filters)
    .queryParam("orders",orders)
    .queryParam("cacheRegion",cacheRegion)
;  List<Tag> aux = restTemplate.getForObject(builder.toUriString(), List<Tag>.class);

 return aux;
}


}