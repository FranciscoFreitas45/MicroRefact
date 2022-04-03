package com.lingxiang2014.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.lingxiang2014.Interface.WithdrawService;
public class WithdrawServiceImpl implements WithdrawService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://6";


public BigDecimal countBalance(Status status,Date beginDate,Date endDate){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/countBalance"))
    .queryParam("status",status)
    .queryParam("beginDate",beginDate)
    .queryParam("endDate",endDate)
;  BigDecimal aux = restTemplate.getForObject(builder.toUriString(), BigDecimal.class);

 return aux;
}


}