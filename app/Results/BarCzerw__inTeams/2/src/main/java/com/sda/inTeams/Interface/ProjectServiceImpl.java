package com.sda.inTeams.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.sda.inTeams.Interface.ProjectService;
public class ProjectServiceImpl implements ProjectService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public List<Project> getAll(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getAll"))
;  List<Project> aux = restTemplate.getForObject(builder.toUriString(), List<Project>.class);

 return aux;
}


}