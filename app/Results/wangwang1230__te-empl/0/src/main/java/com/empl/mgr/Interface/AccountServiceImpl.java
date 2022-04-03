package com.empl.mgr.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.empl.mgr.Interface.AccountService;
public class AccountServiceImpl implements AccountService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


public TeAccount findAccountByName(String userName){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAccountByName"))
    .queryParam("userName",userName)
;  TeAccount aux = restTemplate.getForObject(builder.toUriString(), TeAccount.class);

 return aux;
}


}