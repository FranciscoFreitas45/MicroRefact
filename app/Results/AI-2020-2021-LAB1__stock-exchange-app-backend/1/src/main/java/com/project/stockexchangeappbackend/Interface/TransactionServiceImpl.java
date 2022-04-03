package com.project.stockexchangeappbackend.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.project.stockexchangeappbackend.Interface.TransactionService;
public class TransactionServiceImpl implements TransactionService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public List<Transaction> getTransactionsByStockIdForPricing(Long stockId,Integer amount){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getTransactionsByStockIdForPricing"))
    .queryParam("stockId",stockId)
    .queryParam("amount",amount)
;  List<Transaction> aux = restTemplate.getForObject(builder.toUriString(), List<Transaction>.class);

 return aux;
}


}