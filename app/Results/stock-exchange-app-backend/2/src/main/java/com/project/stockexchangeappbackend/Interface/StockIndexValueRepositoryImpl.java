package com.project.stockexchangeappbackend.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.project.stockexchangeappbackend.Interface.StockIndexValueRepository;
import com.project.stockexchangeappbackend.DTO.Stock;

public class StockIndexValueRepositoryImpl implements StockIndexValueRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public void deleteByStock(Stock stock){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/deleteByStock"))
    .queryParam("stock",stock)
;
  restTemplate.put(builder.toUriString(), null);
}


}