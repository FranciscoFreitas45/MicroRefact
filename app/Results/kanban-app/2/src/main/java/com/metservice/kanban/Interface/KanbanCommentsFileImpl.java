package com.metservice.kanban.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.metservice.kanban.Interface.KanbanCommentsFile;
public class KanbanCommentsFileImpl implements KanbanCommentsFile{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public void readAllComments(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/readAllComments"))
;
  restTemplate.put(builder.toUriString(), null);
}


public List<WorkItemComment> getCommentsFor(int id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getCommentsFor"))
    .queryParam("id",id)
;  List<WorkItemComment> aux = restTemplate.getForObject(builder.toUriString(), List<WorkItemComment>.class);

 return aux;
}


public void writeAllComments(WorkItemTree workItems){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/writeAllComments"))
    .queryParam("workItems",workItems)
;
  restTemplate.put(builder.toUriString(), null);
}


}