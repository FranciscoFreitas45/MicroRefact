package com.easyshopping.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.easyshopping.Interface.ReviewService;
public class ReviewServiceImpl implements ReviewService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


public List<Review> findList(Member member,Product product,Type type,Boolean isShow,Integer count,List<Filter> filters,List<Order> orders,String cacheRegion){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findList"))
    .queryParam("member",member)
    .queryParam("product",product)
    .queryParam("type",type)
    .queryParam("isShow",isShow)
    .queryParam("count",count)
    .queryParam("filters",filters)
    .queryParam("orders",orders)
    .queryParam("cacheRegion",cacheRegion)
;  List<Review> aux = restTemplate.getForObject(builder.toUriString(), List<Review>.class);

 return aux;
}


}