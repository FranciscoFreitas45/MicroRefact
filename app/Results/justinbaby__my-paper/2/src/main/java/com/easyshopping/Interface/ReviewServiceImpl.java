package com.easyshopping.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.easyshopping.Interface.ReviewService;
public class ReviewServiceImpl implements ReviewService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


public Long count(Member member,Product product,Type type,Boolean isShow){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/count"))
    .queryParam("member",member)
    .queryParam("product",product)
    .queryParam("type",type)
    .queryParam("isShow",isShow)
;  Long aux = restTemplate.getForObject(builder.toUriString(), Long.class);

 return aux;
}


}