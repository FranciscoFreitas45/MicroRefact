package com.project.stockexchangeappbackend.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.project.stockexchangeappbackend.Interface.OrderRepository;
public class OrderRepositoryImpl implements OrderRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public List<Order> findByStockAndUserAndOrderTypeAndDateExpirationIsAfterAndDateClosingIsNull(Stock stock,User user,OrderType orderType,OffsetDateTime dateExpiration){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByStockAndUserAndOrderTypeAndDateExpirationIsAfterAndDateClosingIsNull"))
    .queryParam("stock",stock)
    .queryParam("user",user)
    .queryParam("orderType",orderType)
    .queryParam("dateExpiration",dateExpiration)
;  List<Order> aux = restTemplate.getForObject(builder.toUriString(), List<Order>.class);

 return aux;
}


}