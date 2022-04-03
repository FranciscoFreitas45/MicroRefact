package com.lingxiang2014.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.lingxiang2014.Interface.BonudsService;
public class BonudsServiceImpl implements BonudsService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


public BigDecimal countBalance(Type type,Date beginDate,Date endDate){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/countBalance"))
    .queryParam("type",type)
    .queryParam("beginDate",beginDate)
    .queryParam("endDate",endDate)
;  BigDecimal aux = restTemplate.getForObject(builder.toUriString(), BigDecimal.class);

 return aux;
}


public Object save(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/save"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}