package com.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.Interface.WebHijackPreventionStrategy;
public class WebHijackPreventionStrategyImpl implements WebHijackPreventionStrategy{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://23";


public String protect(String str){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/protect"))
    .queryParam("str",str)
;  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


}