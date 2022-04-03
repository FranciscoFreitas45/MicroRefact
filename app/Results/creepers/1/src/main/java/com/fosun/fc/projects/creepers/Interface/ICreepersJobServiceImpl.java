package com.fosun.fc.projects.creepers.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.fosun.fc.projects.creepers.Interface.ICreepersJobService;
public class ICreepersJobServiceImpl implements ICreepersJobService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


public void updateResumeRequestByJobName(String jobName,String request){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/updateResumeRequestByJobName"))
    .queryParam("jobName",jobName)
    .queryParam("request",request)
;
  restTemplate.put(builder.toUriString(), null);
}


}