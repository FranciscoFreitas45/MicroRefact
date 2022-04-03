package com.fosun.fc.projects.creepers.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.fosun.fc.projects.creepers.Interface.ICreepersCourtDisInfoService;
public class ICreepersCourtDisInfoServiceImpl implements ICreepersCourtDisInfoService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://11";


public List<TCreepersCourtDisInfo> findListByName(String name){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findListByName"))
    .queryParam("name",name)
;  List<TCreepersCourtDisInfo> aux = restTemplate.getForObject(builder.toUriString(), List<TCreepersCourtDisInfo>.class);

 return aux;
}


}