package com.hmm.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.hmm.Interface.SalaryOrderRepository;
public class SalaryOrderRepositoryImpl implements SalaryOrderRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://12";


public Float findSalaryByDay(String date){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findSalaryByDay"))
    .queryParam("date",date)
;  Float aux = restTemplate.getForObject(builder.toUriString(), Float.class);

 return aux;
}


}