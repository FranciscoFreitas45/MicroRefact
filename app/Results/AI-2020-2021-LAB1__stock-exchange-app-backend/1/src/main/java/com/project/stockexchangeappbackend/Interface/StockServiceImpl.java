package com.project.stockexchangeappbackend.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.project.stockexchangeappbackend.Interface.StockService;
public class StockServiceImpl implements StockService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


public List<Stock> getAllStocks(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getAllStocks"))
;  List<Stock> aux = restTemplate.getForObject(builder.toUriString(), List<Stock>.class);

 return aux;
}


public Object parallelStream(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/parallelStream"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object peek(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/peek"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public void updateStocks(Collection<Stock> stocks){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/updateStocks"))
    .queryParam("stocks",stocks)
;
  restTemplate.put(builder.toUriString(), null);
}


}