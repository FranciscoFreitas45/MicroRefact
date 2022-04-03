package com.project.stockexchangeappbackend.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.project.stockexchangeappbackend.Interface.StockRepository;
public class StockRepositoryImpl implements StockRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


public Optional<Stock> findByIdAndIsDeletedFalse(Long id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByIdAndIsDeletedFalse"))
    .queryParam("id",id)
;  Optional<Stock> aux = restTemplate.getForObject(builder.toUriString(), Optional<Stock>.class);

 return aux;
}


}