package com.ec.survey.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.ec.survey.Interface.SchemaService;
public class SchemaServiceImpl implements SchemaService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


public Date getLastAnswerSetAnonymDate(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getLastAnswerSetAnonymDate"))
;  Date aux = restTemplate.getForObject(builder.toUriString(), Date.class);

 return aux;
}


public void saveLastAnswerSetAnonymDate(Date lastAnswerSetAnonymisedDate){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/saveLastAnswerSetAnonymDate"))
    .queryParam("lastAnswerSetAnonymisedDate",lastAnswerSetAnonymisedDate)
;
  restTemplate.put(builder.toUriString(), null);
}


}