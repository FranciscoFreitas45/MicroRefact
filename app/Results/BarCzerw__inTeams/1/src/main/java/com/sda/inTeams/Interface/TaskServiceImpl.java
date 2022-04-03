package com.sda.inTeams.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.sda.inTeams.Interface.TaskService;
public class TaskServiceImpl implements TaskService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public List<Task> getAllTasksByUserResponsibleFor(User user){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getAllTasksByUserResponsibleFor"))
    .queryParam("user",user)
;  List<Task> aux = restTemplate.getForObject(builder.toUriString(), List<Task>.class);

 return aux;
}


}