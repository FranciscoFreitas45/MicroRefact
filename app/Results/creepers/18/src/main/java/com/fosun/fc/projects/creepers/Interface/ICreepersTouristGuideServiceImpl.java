package com.fosun.fc.projects.creepers.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.fosun.fc.projects.creepers.Interface.ICreepersTouristGuideService;
public class ICreepersTouristGuideServiceImpl implements ICreepersTouristGuideService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public Map<String,Object> findByParamForMap(CreepersLoginParamDTO param){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByParamForMap"))
    .queryParam("param",param)
;  Map<String,Object> aux = restTemplate.getForObject(builder.toUriString(), Map<String,Object>.class);

 return aux;
}


}