package com.easyshopping.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.easyshopping.Interface.DepositService;
public class DepositServiceImpl implements DepositService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


public Page<Deposit> findPage(Member member,Pageable pageable){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findPage"))
    .queryParam("member",member)
    .queryParam("pageable",pageable)
;  Page<Deposit> aux = restTemplate.getForObject(builder.toUriString(), Page<Deposit>.class);

 return aux;
}


}