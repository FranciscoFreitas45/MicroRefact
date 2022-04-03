package com.metservice.kanban.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.metservice.kanban.Interface.KanbanProject;
public class KanbanProjectImpl implements KanbanProject{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


public KanbanBoardColumnList getColumns(BoardIdentifier boardType){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getColumns"))
    .queryParam("boardType",boardType)
;  KanbanBoardColumnList aux = restTemplate.getForObject(builder.toUriString(), KanbanBoardColumnList.class);

 return aux;
}


}