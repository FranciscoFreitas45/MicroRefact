package com.hmm.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.hmm.Interface.StockRepository;
public class StockRepositoryImpl implements StockRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://9";


public List<Stock> findByStockType(StockType stockType){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByStockType"))
    .queryParam("stockType",stockType)
;  List<Stock> aux = restTemplate.getForObject(builder.toUriString(), List<Stock>.class);

 return aux;
}


}