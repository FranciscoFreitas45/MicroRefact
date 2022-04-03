package com.cym.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.cym.Interface.GroupService;
public class GroupServiceImpl implements GroupService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://6";


public List<Group> getListByParent(String id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getListByParent"))
    .queryParam("id",id)
;  List<Group> aux = restTemplate.getForObject(builder.toUriString(), List<Group>.class);

 return aux;
}


}