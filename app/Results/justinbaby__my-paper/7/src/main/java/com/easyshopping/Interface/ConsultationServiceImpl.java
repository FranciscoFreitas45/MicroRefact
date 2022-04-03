package com.easyshopping.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.easyshopping.Interface.ConsultationService;
public class ConsultationServiceImpl implements ConsultationService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


public List<Consultation> findList(Member member,Product product,Boolean isShow,Integer count,List<Filter> filters,List<Order> orders,String cacheRegion){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findList"))
    .queryParam("member",member)
    .queryParam("product",product)
    .queryParam("isShow",isShow)
    .queryParam("count",count)
    .queryParam("filters",filters)
    .queryParam("orders",orders)
    .queryParam("cacheRegion",cacheRegion)
;  List<Consultation> aux = restTemplate.getForObject(builder.toUriString(), List<Consultation>.class);

 return aux;
}


}