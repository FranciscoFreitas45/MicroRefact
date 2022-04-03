package com.fosun.fc.projects.creepers.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.fosun.fc.projects.creepers.Interface.ICreepersCourtCorpBondsService;
public class ICreepersCourtCorpBondsServiceImpl implements ICreepersCourtCorpBondsService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://7";


public void processByJob(String jobName){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/processByJob"))
    .queryParam("jobName",jobName)
;
  restTemplate.put(builder.toUriString(), null);
}


}