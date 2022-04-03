package com.sda.inTeams.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.sda.inTeams.Interface.ProjectRepository;
public class ProjectRepositoryImpl implements ProjectRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public Object saveAll(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/saveAll"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public List<Project> findAllByProjectOwner(Team team){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAllByProjectOwner"))
    .queryParam("team",team)
;  List<Project> aux = restTemplate.getForObject(builder.toUriString(), List<Project>.class);

 return aux;
}


}