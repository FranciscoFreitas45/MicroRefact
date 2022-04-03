package com.metservice.kanban.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.metservice.kanban.Interface.WorkItemTree;
public class WorkItemTreeImpl implements WorkItemTree{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


public List<WorkItem> getChildrenWithType(int parentId,WorkItemType childType,String workStream){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getChildrenWithType"))
    .queryParam("parentId",parentId)
    .queryParam("childType",childType)
    .queryParam("workStream",workStream)
;  List<WorkItem> aux = restTemplate.getForObject(builder.toUriString(), List<WorkItem>.class);

 return aux;
}


}