package com.fosun.fc.projects.creepers.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.fosun.fc.projects.creepers.Interface.ICreepersErrorListService;
public class ICreepersErrorListServiceImpl implements ICreepersErrorListService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://5";


public void saveError(String merName,String errorDesc,String taskType){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/saveError"))
    .queryParam("merName",merName)
    .queryParam("errorDesc",errorDesc)
    .queryParam("taskType",taskType)
;
  restTemplate.put(builder.toUriString(), null);
}


}