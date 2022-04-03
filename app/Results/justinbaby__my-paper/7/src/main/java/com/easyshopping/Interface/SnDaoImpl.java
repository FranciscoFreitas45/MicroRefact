package com.easyshopping.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.easyshopping.Interface.SnDao;
public class SnDaoImpl implements SnDao{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public String generate(Type type){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/generate"))
    .queryParam("type",type)
;  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


}