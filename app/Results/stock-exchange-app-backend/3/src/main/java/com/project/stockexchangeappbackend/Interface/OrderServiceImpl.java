package com.project.stockexchangeappbackend.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.project.stockexchangeappbackend.Interface.OrderService;
public class OrderServiceImpl implements OrderService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public AllOrders findOrderById(Long id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findOrderById"))
    .queryParam("id",id)
;  AllOrders aux = restTemplate.getForObject(builder.toUriString(), AllOrders.class);

 return aux;
}


public void createOrder(CreateOrderDTO orderDTO){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/createOrder"))
    .queryParam("orderDTO",orderDTO)
;
  restTemplate.put(builder.toUriString(), null);
}


public Page<AllOrders> findAllOrders(Pageable pageable,Specification<AllOrders> specification){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAllOrders"))
    .queryParam("pageable",pageable)
    .queryParam("specification",specification)
;  Page<AllOrders> aux = restTemplate.getForObject(builder.toUriString(), Page<AllOrders>.class);

 return aux;
}


public void deactivateOrder(Long id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/deactivateOrder"))
    .queryParam("id",id)
;
  restTemplate.put(builder.toUriString(), null);
}


}