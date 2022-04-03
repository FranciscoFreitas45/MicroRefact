package com.fosun.fc.projects.creepers.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.fosun.fc.projects.creepers.Interface.ICreepersJudgementService;
public class ICreepersJudgementServiceImpl implements ICreepersJudgementService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


public List<TCreepersJudgement> findByMerName(String merName){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByMerName"))
    .queryParam("merName",merName)
;  List<TCreepersJudgement> aux = restTemplate.getForObject(builder.toUriString(), List<TCreepersJudgement>.class);

 return aux;
}


}