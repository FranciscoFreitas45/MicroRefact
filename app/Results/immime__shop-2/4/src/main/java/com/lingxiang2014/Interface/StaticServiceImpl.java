package com.lingxiang2014.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.lingxiang2014.Interface.StaticService;
public class StaticServiceImpl implements StaticService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


public int buildOther(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/buildOther"))
;  int aux = restTemplate.getForObject(builder.toUriString(), int.class);

 return aux;
}


public int build(Article article){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/build"))
    .queryParam("article",article)
;  int aux = restTemplate.getForObject(builder.toUriString(), int.class);

 return aux;
}


public int buildIndex(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/buildIndex"))
;  int aux = restTemplate.getForObject(builder.toUriString(), int.class);

 return aux;
}


}