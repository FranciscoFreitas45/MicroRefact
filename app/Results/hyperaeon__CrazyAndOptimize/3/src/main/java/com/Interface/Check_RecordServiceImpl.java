package com.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.Interface.Check_RecordService;
public class Check_RecordServiceImpl implements Check_RecordService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


public List<Check_Record> findCreditCardApproval(Integer reportId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findCreditCardApproval"))
    .queryParam("reportId",reportId)
;  List<Check_Record> aux = restTemplate.getForObject(builder.toUriString(), List<Check_Record>.class);

 return aux;
}


public List<Check_Record> findPersonageQuery(Integer reportId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findPersonageQuery"))
    .queryParam("reportId",reportId)
;  List<Check_Record> aux = restTemplate.getForObject(builder.toUriString(), List<Check_Record>.class);

 return aux;
}


public List<Check_Record> findLoanAfterManager(Integer reportId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findLoanAfterManager"))
    .queryParam("reportId",reportId)
;  List<Check_Record> aux = restTemplate.getForObject(builder.toUriString(), List<Check_Record>.class);

 return aux;
}


public List<Check_Record> findLoanApproval(Integer reportId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findLoanApproval"))
    .queryParam("reportId",reportId)
;  List<Check_Record> aux = restTemplate.getForObject(builder.toUriString(), List<Check_Record>.class);

 return aux;
}


}