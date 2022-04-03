package com.fosun.fc.projects.creepers.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.fosun.fc.projects.creepers.Interface.ICreepersMedicalService;
public class ICreepersMedicalServiceImpl implements ICreepersMedicalService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


public void processByJob(String jobName){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/processByJob"))
    .queryParam("jobName",jobName)
;
  restTemplate.put(builder.toUriString(), null);
}


}