package com.ec.survey.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.ec.survey.Interface.SchemaService;
public class SchemaServiceImpl implements SchemaService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


public void saveLastLDAPSynchronization2Date(Date syncDate){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/saveLastLDAPSynchronization2Date"))
    .queryParam("syncDate",syncDate)
;
  restTemplate.put(builder.toUriString(), null);
}


}