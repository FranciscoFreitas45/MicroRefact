package com.yalcin.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.yalcin.Interface.JwtProvider;
public class JwtProviderImpl implements JwtProvider{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://5";


public String getSubjectFromJwt(String token,String matter){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getSubjectFromJwt"))
    .queryParam("token",token)
    .queryParam("matter",matter)
;  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


}