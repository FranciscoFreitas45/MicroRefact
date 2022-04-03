package com.example.steam.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.steam.Interface.SensitiveWordService;
public class SensitiveWordServiceImpl implements SensitiveWordService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://8";


public void addSensitiveWord(String word){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/addSensitiveWord"))
    .queryParam("word",word)
;
  restTemplate.put(builder.toUriString(), null);
}


}