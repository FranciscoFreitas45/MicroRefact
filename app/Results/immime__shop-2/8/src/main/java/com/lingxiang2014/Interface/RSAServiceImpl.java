package com.lingxiang2014.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.lingxiang2014.Interface.RSAService;
public class RSAServiceImpl implements RSAService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public RSAPublicKey generateKey(HttpServletRequest request){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/generateKey"))
    .queryParam("request",request)
;  RSAPublicKey aux = restTemplate.getForObject(builder.toUriString(), RSAPublicKey.class);

 return aux;
}


}