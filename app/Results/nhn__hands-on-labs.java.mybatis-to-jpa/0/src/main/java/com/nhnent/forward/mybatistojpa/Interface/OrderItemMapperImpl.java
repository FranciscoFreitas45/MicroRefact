package com.nhnent.forward.mybatistojpa.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.nhnent.forward.mybatistojpa.Interface.OrderItemMapper;
public class OrderItemMapperImpl implements OrderItemMapper{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public int insertOrderItem(OrderItem orderItem){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/insertOrderItem"))
    .queryParam("orderItem",orderItem)
;  int aux = restTemplate.getForObject(builder.toUriString(), int.class);

 return aux;
}


public int deleteOrderItem(Long orderId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/deleteOrderItem"))
    .queryParam("orderId",orderId)
;  int aux = restTemplate.getForObject(builder.toUriString(), int.class);

 return aux;
}


}