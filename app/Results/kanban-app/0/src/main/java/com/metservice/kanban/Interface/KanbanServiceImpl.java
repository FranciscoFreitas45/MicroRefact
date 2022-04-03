package com.metservice.kanban.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.metservice.kanban.Interface.KanbanService;
public class KanbanServiceImpl implements KanbanService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


public KanbanProject getKanbanProject(String projectName){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getKanbanProject"))
    .queryParam("projectName",projectName)
;  KanbanProject aux = restTemplate.getForObject(builder.toUriString(), KanbanProject.class);

 return aux;
}


}