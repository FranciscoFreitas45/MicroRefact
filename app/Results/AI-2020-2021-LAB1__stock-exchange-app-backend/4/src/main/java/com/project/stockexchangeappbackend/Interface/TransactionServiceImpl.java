package com.project.stockexchangeappbackend.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.project.stockexchangeappbackend.Interface.TransactionService;
public class TransactionServiceImpl implements TransactionService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public Page<Transaction> getOwnedTransactions(Pageable pageable,Specification<Transaction> specification,boolean isSeller,boolean isBuyer){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getOwnedTransactions"))
    .queryParam("pageable",pageable)
    .queryParam("specification",specification)
    .queryParam("isSeller",isSeller)
    .queryParam("isBuyer",isBuyer)
;  Page<Transaction> aux = restTemplate.getForObject(builder.toUriString(), Page<Transaction>.class);

 return aux;
}


public Page<Transaction> getUserTransactions(Pageable pageable,Specification<Transaction> specification,Long userId,boolean isSeller,boolean isBuyer){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getUserTransactions"))
    .queryParam("pageable",pageable)
    .queryParam("specification",specification)
    .queryParam("userId",userId)
    .queryParam("isSeller",isSeller)
    .queryParam("isBuyer",isBuyer)
;  Page<Transaction> aux = restTemplate.getForObject(builder.toUriString(), Page<Transaction>.class);

 return aux;
}


}