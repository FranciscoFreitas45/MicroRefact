package com.lingxiang2014.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.lingxiang2014.Interface.BonudsService;
public class BonudsServiceImpl implements BonudsService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


public BigDecimal count(Member member,Type type,Date beginDate,Date endDate){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/count"))
    .queryParam("member",member)
    .queryParam("type",type)
    .queryParam("beginDate",beginDate)
    .queryParam("endDate",endDate)
;  BigDecimal aux = restTemplate.getForObject(builder.toUriString(), BigDecimal.class);

 return aux;
}


public List<Bonuds> findList(Member member,Type type,Date beginDate,Date endDate){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findList"))
    .queryParam("member",member)
    .queryParam("type",type)
    .queryParam("beginDate",beginDate)
    .queryParam("endDate",endDate)
;  List<Bonuds> aux = restTemplate.getForObject(builder.toUriString(), List<Bonuds>.class);

 return aux;
}


public List<Bonuds> findToday(Type type,Member member){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findToday"))
    .queryParam("type",type)
    .queryParam("member",member)
;  List<Bonuds> aux = restTemplate.getForObject(builder.toUriString(), List<Bonuds>.class);

 return aux;
}


public Object save(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/save"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}