package com.sda.inTeams.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.sda.inTeams.Interface.TeamRepository;
import java.util.*;
import com.sda.inTeams.model.Project.*;
import com.sda.inTeams.DTO.*;
public class TeamRepositoryImpl implements TeamRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://5";


public Optional<Team> findById(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findById"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return(Optional<Team>) aux;
}


public Optional<Team> findByProjectsContaining(Project project){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByProjectsContaining"))
    .queryParam("project",project)
;  Optional<Team> aux = restTemplate.getForObject(builder.toUriString(), Optional.class);

 return aux;
}


public Object orElseThrow(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/orElseThrow"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object save(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/save"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}