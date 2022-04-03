package com.fosun.fc.projects.creepers.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.fosun.fc.projects.creepers.Interface.ICreepersShixinService;
public class ICreepersShixinServiceImpl implements ICreepersShixinService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://5";


public void processByMerName(String merName){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/processByMerName"))
    .queryParam("merName",merName)
;
  restTemplate.put(builder.toUriString(), null);
}


}