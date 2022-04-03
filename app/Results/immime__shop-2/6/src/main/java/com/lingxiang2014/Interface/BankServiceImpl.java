package com.lingxiang2014.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.lingxiang2014.Interface.BankService;
public class BankServiceImpl implements BankService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://5";


public Object find(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/find"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public List<Bank> findListByMember(Member member){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findListByMember"))
    .queryParam("member",member)
;  List<Bank> aux = restTemplate.getForObject(builder.toUriString(), List<Bank>.class);

 return aux;
}


}