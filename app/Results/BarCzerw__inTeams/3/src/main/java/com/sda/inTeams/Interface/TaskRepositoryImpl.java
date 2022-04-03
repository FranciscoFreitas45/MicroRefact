package com.sda.inTeams.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.sda.inTeams.Interface.TaskRepository;
public class TaskRepositoryImpl implements TaskRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public Object saveAll(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/saveAll"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public List<Task> findAllByProject(Project project){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAllByProject"))
    .queryParam("project",project)
;  List<Task> aux = restTemplate.getForObject(builder.toUriString(), List<Task>.class);

 return aux;
}


public List<Task> findAllByUserResponsible(User user){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAllByUserResponsible"))
    .queryParam("user",user)
;  List<Task> aux = restTemplate.getForObject(builder.toUriString(), List<Task>.class);

 return aux;
}


}