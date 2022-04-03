package com.example.demo.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.Interface.OrderService;
public class OrderServiceImpl implements OrderService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public Order save(Order entity){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/save"))
    .queryParam("entity",entity)
;  Order aux = restTemplate.getForObject(builder.toUriString(), Order.class);

 return aux;
}


public List<Order> findByUsername(String username){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByUsername"))
    .queryParam("username",username)
;  List<Order> aux = restTemplate.getForObject(builder.toUriString(), List<Order>.class);

 return aux;
}


public Order getOne(Long id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getOne"))
    .queryParam("id",id)
;  Order aux = restTemplate.getForObject(builder.toUriString(), Order.class);

 return aux;
}


}