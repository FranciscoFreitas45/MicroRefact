package com.metservice.kanban.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.metservice.kanban.Interface.KanbanProject;
public class KanbanProjectImpl implements KanbanProject{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


public WorkItemTree getWorkItemTree(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getWorkItemTree"))
;  WorkItemTree aux = restTemplate.getForObject(builder.toUriString(), WorkItemTree.class);

 return aux;
}


}