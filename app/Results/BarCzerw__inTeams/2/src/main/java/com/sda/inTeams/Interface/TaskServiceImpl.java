package com.sda.inTeams.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.sda.inTeams.Interface.TaskService;
public class TaskServiceImpl implements TaskService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public List<Task> getAll(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getAll"))
;  List<Task> aux = restTemplate.getForObject(builder.toUriString(), List<Task>.class);

 return aux;
}


}