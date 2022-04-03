package com.lingxiang2014.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.lingxiang2014.Interface.MemberService;
public class MemberServiceImpl implements MemberService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public long count(MemberRank memberRank,Date beginDate,Date endDate){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/count"))
    .queryParam("memberRank",memberRank)
    .queryParam("beginDate",beginDate)
    .queryParam("endDate",endDate)
;  long aux = restTemplate.getForObject(builder.toUriString(), long.class);

 return aux;
}


public BigDecimal countBalance(Integer type,Date beginDate,Date endDate){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/countBalance"))
    .queryParam("type",type)
    .queryParam("beginDate",beginDate)
    .queryParam("endDate",endDate)
;  BigDecimal aux = restTemplate.getForObject(builder.toUriString(), BigDecimal.class);

 return aux;
}


public Object findAll(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAll"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public void update(Member member,Integer modifyPoint,BigDecimal modifyBalance,String depositMemo,Admin operator){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/update"))
    .queryParam("member",member)
    .queryParam("modifyPoint",modifyPoint)
    .queryParam("modifyBalance",modifyBalance)
    .queryParam("depositMemo",depositMemo)
    .queryParam("operator",operator)
;
  restTemplate.put(builder.toUriString(), null);
}


}