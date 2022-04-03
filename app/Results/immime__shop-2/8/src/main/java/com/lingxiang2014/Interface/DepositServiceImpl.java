package com.lingxiang2014.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.lingxiang2014.Interface.DepositService;
public class DepositServiceImpl implements DepositService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public BigDecimal countBalance(Type type,Date beginDate,Date endDate){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/countBalance"))
    .queryParam("type",type)
    .queryParam("beginDate",beginDate)
    .queryParam("endDate",endDate)
;  BigDecimal aux = restTemplate.getForObject(builder.toUriString(), BigDecimal.class);

 return aux;
}


public BigDecimal count(Member member,Type type,Date beginDate,Date endDate){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/count"))
    .queryParam("member",member)
    .queryParam("type",type)
    .queryParam("beginDate",beginDate)
    .queryParam("endDate",endDate)
;  BigDecimal aux = restTemplate.getForObject(builder.toUriString(), BigDecimal.class);

 return aux;
}


}