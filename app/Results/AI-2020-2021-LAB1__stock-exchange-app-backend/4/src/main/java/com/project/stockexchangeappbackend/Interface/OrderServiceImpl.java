package com.project.stockexchangeappbackend.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.project.stockexchangeappbackend.Interface.OrderService;
public class OrderServiceImpl implements OrderService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public Page<AllOrders> getOwnedOrders(Pageable pageable,Specification<AllOrders> specification){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getOwnedOrders"))
    .queryParam("pageable",pageable)
    .queryParam("specification",specification)
;  Page<AllOrders> aux = restTemplate.getForObject(builder.toUriString(), Page<AllOrders>.class);

 return aux;
}


public Page<AllOrders> getOrdersByUser(Pageable pageable,Specification<AllOrders> specification,Long id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getOrdersByUser"))
    .queryParam("pageable",pageable)
    .queryParam("specification",specification)
    .queryParam("id",id)
;  Page<AllOrders> aux = restTemplate.getForObject(builder.toUriString(), Page<AllOrders>.class);

 return aux;
}


}