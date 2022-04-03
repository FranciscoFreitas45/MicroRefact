package com.cym.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.cym.Interface.ConfService;
public class ConfServiceImpl implements ConfService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://7";


public void replace(String nginxPath,String nginxContent,List<String> subContent,List<String> subName,Boolean isBak,String adminName){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/replace"))
    .queryParam("nginxPath",nginxPath)
    .queryParam("nginxContent",nginxContent)
    .queryParam("subContent",subContent)
    .queryParam("subName",subName)
    .queryParam("isBak",isBak)
    .queryParam("adminName",adminName)
;
  restTemplate.put(builder.toUriString(), null);
}


public ConfExt buildConf(Boolean decompose,Boolean check){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/buildConf"))
    .queryParam("decompose",decompose)
    .queryParam("check",check)
;  ConfExt aux = restTemplate.getForObject(builder.toUriString(), ConfExt.class);

 return aux;
}


}