package com.yalcin.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.yalcin.Interface.JwtProvider;
public class JwtProviderImpl implements JwtProvider{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://5";


public boolean validateJwtToken(String authToken,String matter,HttpServletRequest request){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/validateJwtToken"))
    .queryParam("authToken",authToken)
    .queryParam("matter",matter)
    .queryParam("request",request)
;  boolean aux = restTemplate.getForObject(builder.toUriString(), boolean.class);

 return aux;
}


}