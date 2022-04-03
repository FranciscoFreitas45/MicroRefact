package com.metservice.kanban.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.metservice.kanban.Interface.WorkItemTypeCollection;
public class WorkItemTypeCollectionImpl implements WorkItemTypeCollection{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


public TreeNode<WorkItemType> getRoot(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getRoot"))
;  TreeNode<WorkItemType> aux = restTemplate.getForObject(builder.toUriString(), TreeNode<WorkItemType>.class);

 return aux;
}


}