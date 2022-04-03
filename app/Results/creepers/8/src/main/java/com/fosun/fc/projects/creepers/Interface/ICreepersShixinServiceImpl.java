package com.fosun.fc.projects.creepers.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.fosun.fc.projects.creepers.Interface.ICreepersShixinService;
public class ICreepersShixinServiceImpl implements ICreepersShixinService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://5";


public List<TCreepersShixin> findListByMerName(String merName){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findListByMerName"))
    .queryParam("merName",merName)
;  List<TCreepersShixin> aux = restTemplate.getForObject(builder.toUriString(), List<TCreepersShixin>.class);

 return aux;
}


}