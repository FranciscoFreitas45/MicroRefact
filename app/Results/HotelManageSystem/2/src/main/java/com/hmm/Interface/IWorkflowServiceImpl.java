package com.hmm.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.hmm.Interface.IWorkflowService;
public class IWorkflowServiceImpl implements IWorkflowService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public void deleteUser2(String name){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/deleteUser2"))
    .queryParam("name",name)
;
  restTemplate.put(builder.toUriString(), null);
}


public void addUser2(String name,String password){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/addUser2"))
    .queryParam("name",name)
    .queryParam("password",password)
;
  restTemplate.put(builder.toUriString(), null);
}


public void deleteUser(String name,String groupName){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/deleteUser"))
    .queryParam("name",name)
    .queryParam("groupName",groupName)
;
  restTemplate.put(builder.toUriString(), null);
}


}