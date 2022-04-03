package com.metservice.kanban.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.metservice.kanban.Interface.WorkItem;
public class WorkItemImpl implements WorkItem{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


}