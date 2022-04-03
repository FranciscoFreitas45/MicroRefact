package com.yalcin.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.yalcin.Interface.OrderRepository;
public class OrderRepositoryImpl implements OrderRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


public List<Order> findAllByUsername(Integer userId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAllByUsername"))
    .queryParam("userId",userId)
;  List<Order> aux = restTemplate.getForObject(builder.toUriString(), List<Order>.class);

 return aux;
}


}