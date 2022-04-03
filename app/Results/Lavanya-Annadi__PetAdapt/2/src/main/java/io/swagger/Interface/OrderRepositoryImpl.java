package io.swagger.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.Interface.OrderRepository;
public class OrderRepositoryImpl implements OrderRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public List<Order> findByUser(User user){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByUser"))
    .queryParam("user",user)
;  List<Order> aux = restTemplate.getForObject(builder.toUriString(), List<Order>.class);

 return aux;
}


}