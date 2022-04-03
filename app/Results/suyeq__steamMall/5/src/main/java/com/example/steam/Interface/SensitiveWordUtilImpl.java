package com.example.steam.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.steam.Interface.SensitiveWordUtil;
public class SensitiveWordUtilImpl implements SensitiveWordUtil{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://8";


public String replaceSensitiveWord(String text){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/replaceSensitiveWord"))
    .queryParam("text",text)
;  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


}