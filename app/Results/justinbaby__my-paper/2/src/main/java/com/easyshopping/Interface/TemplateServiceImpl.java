package com.easyshopping.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.easyshopping.Interface.TemplateService;
public class TemplateServiceImpl implements TemplateService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://9";


public Template get(String id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/get"))
    .queryParam("id",id)
;  Template aux = restTemplate.getForObject(builder.toUriString(), Template.class);

 return aux;
}


}