package com.zammc.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.zammc.Interface.OrderService;
public class OrderServiceImpl implements OrderService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public Long queryOrderCount(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/queryOrderCount"))
;  Long aux = restTemplate.getForObject(builder.toUriString(), Long.class);

 return aux;
}


public Double queryTotalPrice(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/queryTotalPrice"))
;  Double aux = restTemplate.getForObject(builder.toUriString(), Double.class);

 return aux;
}


}