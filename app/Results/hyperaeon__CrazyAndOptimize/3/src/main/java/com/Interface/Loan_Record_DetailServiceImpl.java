package com.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.Interface.Loan_Record_DetailService;
public class Loan_Record_DetailServiceImpl implements Loan_Record_DetailService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


public List<Loan_Record_Detail> findCreditCardOverdue(Integer reportId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findCreditCardOverdue"))
    .queryParam("reportId",reportId)
;  List<Loan_Record_Detail> aux = restTemplate.getForObject(builder.toUriString(), List<Loan_Record_Detail>.class);

 return aux;
}


public List<Loan_Record_Detail> findCreditCardNoOverdue(Integer reportId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findCreditCardNoOverdue"))
    .queryParam("reportId",reportId)
;  List<Loan_Record_Detail> aux = restTemplate.getForObject(builder.toUriString(), List<Loan_Record_Detail>.class);

 return aux;
}


public List<Loan_Record_Detail> findCreditCardOverdueSixty(Integer reportId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findCreditCardOverdueSixty"))
    .queryParam("reportId",reportId)
;  List<Loan_Record_Detail> aux = restTemplate.getForObject(builder.toUriString(), List<Loan_Record_Detail>.class);

 return aux;
}


public List<Loan_Record_Detail> findHouserLoadNoOverdue(Integer reportId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findHouserLoadNoOverdue"))
    .queryParam("reportId",reportId)
;  List<Loan_Record_Detail> aux = restTemplate.getForObject(builder.toUriString(), List<Loan_Record_Detail>.class);

 return aux;
}


public List<Loan_Record_Detail> findHouserLoadOverdue(Integer reportId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findHouserLoadOverdue"))
    .queryParam("reportId",reportId)
;  List<Loan_Record_Detail> aux = restTemplate.getForObject(builder.toUriString(), List<Loan_Record_Detail>.class);

 return aux;
}


public List<Loan_Record_Detail> findOtherLoadNoOverdue(Integer reportId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findOtherLoadNoOverdue"))
    .queryParam("reportId",reportId)
;  List<Loan_Record_Detail> aux = restTemplate.getForObject(builder.toUriString(), List<Loan_Record_Detail>.class);

 return aux;
}


public List<Loan_Record_Detail> findOtherLoadOverdue(Integer reportId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findOtherLoadOverdue"))
    .queryParam("reportId",reportId)
;  List<Loan_Record_Detail> aux = restTemplate.getForObject(builder.toUriString(), List<Loan_Record_Detail>.class);

 return aux;
}


}