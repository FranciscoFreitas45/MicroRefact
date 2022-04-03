package com.cym.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.cym.Interface.AdminService;
public class AdminServiceImpl implements AdminService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


public Admin getByToken(String token){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getByToken"))
    .queryParam("token",token)
;  Admin aux = restTemplate.getForObject(builder.toUriString(), Admin.class);

 return aux;
}


public Admin getByCreditKey(String creditKey){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getByCreditKey"))
    .queryParam("creditKey",creditKey)
;  Admin aux = restTemplate.getForObject(builder.toUriString(), Admin.class);

 return aux;
}


}