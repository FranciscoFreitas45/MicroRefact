package com.fosun.fc.projects.creepers.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.fosun.fc.projects.creepers.Interface.ICreepersCourtDishonestyService;
public class ICreepersCourtDishonestyServiceImpl implements ICreepersCourtDishonestyService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://11";


public void processByJob(String JobName){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/processByJob"))
    .queryParam("JobName",JobName)
;
  restTemplate.put(builder.toUriString(), null);
}


}