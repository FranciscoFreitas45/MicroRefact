package com.ec.survey.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.ec.survey.Interface.Form;
public class FormImpl implements Form{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://10";


public void setStatistics(Statistics statistics){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setStatistics"))
    .queryParam("statistics",statistics)
;
  restTemplate.put(builder.toUriString(), null);
}


public Survey getSurvey(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getSurvey"))
;  Survey aux = restTemplate.getForObject(builder.toUriString(), Survey.class);

 return aux;
}


}