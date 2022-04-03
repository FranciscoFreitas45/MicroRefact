package com.example.demo.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.Interface.OrderDetailService;
public class OrderDetailServiceImpl implements OrderDetailService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public OrderDetail save(OrderDetail entity){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/save"))
    .queryParam("entity",entity)
;  OrderDetail aux = restTemplate.getForObject(builder.toUriString(), OrderDetail.class);

 return aux;
}


public List<OrderDetail> findByOrderId(long id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByOrderId"))
    .queryParam("id",id)
;  List<OrderDetail> aux = restTemplate.getForObject(builder.toUriString(), List<OrderDetail>.class);

 return aux;
}


}