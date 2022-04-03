package com.hmm.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.hmm.Interface.IWorkflowService;
public class IWorkflowServiceImpl implements IWorkflowService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


public void addGroup(String id,String name,String type){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/addGroup"))
    .queryParam("id",id)
    .queryParam("name",name)
    .queryParam("type",type)
;
  restTemplate.put(builder.toUriString(), null);
}


public void deleteGroup(String id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/deleteGroup"))
    .queryParam("id",id)
;
  restTemplate.put(builder.toUriString(), null);
}


}