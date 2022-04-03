package com.poseidon.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.poseidon.Interface.TransactionService;
public class TransactionServiceImpl implements TransactionService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://5";


public TransactionReportVO fetchTransactionFromTag(String tagNo){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/fetchTransactionFromTag"))
    .queryParam("tagNo",tagNo)
;  TransactionReportVO aux = restTemplate.getForObject(builder.toUriString(), TransactionReportVO.class);

 return aux;
}


public List<TransactionVO> listAllTransactions(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/listAllTransactions"))
;  List<TransactionVO> aux = restTemplate.getForObject(builder.toUriString(), List<TransactionVO>.class);

 return aux;
}


public List<String> allTagNumbers(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/allTagNumbers"))
;  List<String> aux = restTemplate.getForObject(builder.toUriString(), List<String>.class);

 return aux;
}


}