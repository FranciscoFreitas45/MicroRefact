package com.poseidon.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.poseidon.Interface.TransactionDAO;
public class TransactionDAOImpl implements TransactionDAO{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public List<TransactionVO> listAllTransactions(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/listAllTransactions"))
;  List<TransactionVO> aux = restTemplate.getForObject(builder.toUriString(), List<TransactionVO>.class);

 return aux;
}


public List<TransactionVO> listTodaysTransactions(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/listTodaysTransactions"))
;  List<TransactionVO> aux = restTemplate.getForObject(builder.toUriString(), List<TransactionVO>.class);

 return aux;
}


public String saveTransaction(TransactionVO currentTransaction){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/saveTransaction"))
    .queryParam("currentTransaction",currentTransaction)
;  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


public List<TransactionVO> searchTransactions(TransactionVO searchTransaction){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/searchTransactions"))
    .queryParam("searchTransaction",searchTransaction)
;  List<TransactionVO> aux = restTemplate.getForObject(builder.toUriString(), List<TransactionVO>.class);

 return aux;
}


public TransactionVO fetchTransactionFromId(Long id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/fetchTransactionFromId"))
    .queryParam("id",id)
;  TransactionVO aux = restTemplate.getForObject(builder.toUriString(), TransactionVO.class);

 return aux;
}


public TransactionReportVO fetchTransactionFromTag(String tagNo){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/fetchTransactionFromTag"))
    .queryParam("tagNo",tagNo)
;  TransactionReportVO aux = restTemplate.getForObject(builder.toUriString(), TransactionReportVO.class);

 return aux;
}


public void updateTransaction(TransactionVO currentTransaction){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/updateTransaction"))
    .queryParam("currentTransaction",currentTransaction)
;
  restTemplate.put(builder.toUriString(), null);
}


public void deleteTransaction(Long id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/deleteTransaction"))
    .queryParam("id",id)
;
  restTemplate.put(builder.toUriString(), null);
}


public void updateTransactionStatus(Long id,String status){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/updateTransactionStatus"))
    .queryParam("id",id)
    .queryParam("status",status)
;
  restTemplate.put(builder.toUriString(), null);
}


public List<String> allTagNumbers(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/allTagNumbers"))
;  List<String> aux = restTemplate.getForObject(builder.toUriString(), List<String>.class);

 return aux;
}


}