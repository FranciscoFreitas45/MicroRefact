package com.project.stockexchangeappbackend.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.project.stockexchangeappbackend.Interface.OrderRepository;
import java.util.*;
import com.project.stockexchangeappbackend.DTO.Stock;
import com.project.stockexchangeappbackend.DTO.Order;
import com.project.stockexchangeappbackend.DTO.OrderType;
import com.project.stockexchangeappbackend.DTO.User;
import java.time.*;
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
;  List<Order> aux = restTemplate.getForObject(builder.toUriString(), List.class);

 return aux;
}


}