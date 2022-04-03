package com.metservice.kanban.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.metservice.kanban.Interface.EstimatesDao;
public class EstimatesDaoImpl implements EstimatesDao{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public EstimatesProject loadProject(String projectName){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/loadProject"))
    .queryParam("projectName",projectName)
;  EstimatesProject aux = restTemplate.getForObject(builder.toUriString(), EstimatesProject.class);

 return aux;
}


}