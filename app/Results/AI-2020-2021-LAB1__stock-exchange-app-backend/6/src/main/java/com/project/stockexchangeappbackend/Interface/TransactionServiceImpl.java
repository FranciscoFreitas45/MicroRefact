package com.project.stockexchangeappbackend.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.project.stockexchangeappbackend.Interface.TransactionService;
public class TransactionServiceImpl implements TransactionService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public Transaction findTransactionById(Long id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findTransactionById"))
    .queryParam("id",id)
;  Transaction aux = restTemplate.getForObject(builder.toUriString(), Transaction.class);

 return aux;
}


public Page<Transaction> findAllTransactions(Pageable pageable,Specification<Transaction> specification){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAllTransactions"))
    .queryParam("pageable",pageable)
    .queryParam("specification",specification)
;  Page<Transaction> aux = restTemplate.getForObject(builder.toUriString(), Page<Transaction>.class);

 return aux;
}


}