package com.hmm.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.hmm.Interface.GroupRoleService;
public class GroupRoleServiceImpl implements GroupRoleService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


public GroupRole findByGroupName(String groupName){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByGroupName"))
    .queryParam("groupName",groupName)
;  GroupRole aux = restTemplate.getForObject(builder.toUriString(), GroupRole.class);

 return aux;
}


}