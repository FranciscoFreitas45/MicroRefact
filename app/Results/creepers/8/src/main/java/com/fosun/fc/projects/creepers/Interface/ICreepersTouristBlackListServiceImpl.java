package com.fosun.fc.projects.creepers.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.fosun.fc.projects.creepers.Interface.ICreepersTouristBlackListService;
public class ICreepersTouristBlackListServiceImpl implements ICreepersTouristBlackListService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public List<TCreepersTourBlackList> findListByAgentNameAndType(String merName){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findListByAgentNameAndType"))
    .queryParam("merName",merName)
;  List<TCreepersTourBlackList> aux = restTemplate.getForObject(builder.toUriString(), List<TCreepersTourBlackList>.class);

 return aux;
}


public List<TCreepersTourBlackList> findListByGuideNo(String merName){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findListByGuideNo"))
    .queryParam("merName",merName)
;  List<TCreepersTourBlackList> aux = restTemplate.getForObject(builder.toUriString(), List<TCreepersTourBlackList>.class);

 return aux;
}


}