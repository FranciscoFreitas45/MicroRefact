package com.project.stockexchangeappbackend.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.project.stockexchangeappbackend.Interface.StockRepository;
import java.util.*;
import com.project.stockexchangeappbackend.DTO.Stock;
public class StockRepositoryImpl implements StockRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public Optional<Stock> findByIdAndIsDeletedFalse(Long id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByIdAndIsDeletedFalse"))
    .queryParam("id",id)
;  Optional<Stock> aux = restTemplate.getForObject(builder.toUriString(), Optional.class);

 return aux;
}


}