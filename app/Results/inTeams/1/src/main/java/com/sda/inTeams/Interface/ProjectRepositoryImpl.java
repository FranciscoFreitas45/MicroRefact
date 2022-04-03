package com.sda.inTeams.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.sda.inTeams.Interface.ProjectRepository;
import com.sda.inTeams.DTO.*;
import java.util.*;
public class ProjectRepositoryImpl implements ProjectRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public Optional<Project> findByTasksContaining(Task task){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByTasksContaining"))
    .queryParam("task",task)
;  Optional<Project> aux = restTemplate.getForObject(builder.toUriString(), Optional.class);

 return aux;
}


public Object orElseThrow(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/orElseThrow"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}