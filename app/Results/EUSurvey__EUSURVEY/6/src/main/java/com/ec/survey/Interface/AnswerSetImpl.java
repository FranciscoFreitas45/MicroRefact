package com.ec.survey.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.ec.survey.Interface.AnswerSet;
public class AnswerSetImpl implements AnswerSet{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public Survey getSurvey(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getSurvey"))
;  Survey aux = restTemplate.getForObject(builder.toUriString(), Survey.class);

 return aux;
}


}