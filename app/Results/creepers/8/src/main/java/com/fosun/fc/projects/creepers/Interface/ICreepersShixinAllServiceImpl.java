package com.fosun.fc.projects.creepers.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.fosun.fc.projects.creepers.Interface.ICreepersShixinAllService;
public class ICreepersShixinAllServiceImpl implements ICreepersShixinAllService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://6";


public List<TCreepersShixinAll> findListByName(String name){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findListByName"))
    .queryParam("name",name)
;  List<TCreepersShixinAll> aux = restTemplate.getForObject(builder.toUriString(), List<TCreepersShixinAll>.class);

 return aux;
}


}