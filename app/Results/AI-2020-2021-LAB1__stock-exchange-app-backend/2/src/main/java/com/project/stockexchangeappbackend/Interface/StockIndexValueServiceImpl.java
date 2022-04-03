package com.project.stockexchangeappbackend.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.project.stockexchangeappbackend.Interface.StockIndexValueService;
public class StockIndexValueServiceImpl implements StockIndexValueService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public List<StockIndexValueDTO> getStockIndexValues(Long stockId,Specification<StockIndexValue> specification,Integer interval){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getStockIndexValues"))
    .queryParam("stockId",stockId)
    .queryParam("specification",specification)
    .queryParam("interval",interval)
;  List<StockIndexValueDTO> aux = restTemplate.getForObject(builder.toUriString(), List<StockIndexValueDTO>.class);

 return aux;
}


}