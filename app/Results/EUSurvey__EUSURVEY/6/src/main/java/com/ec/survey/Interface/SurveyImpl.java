package com.ec.survey.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.ec.survey.Interface.Survey;
public class SurveyImpl implements Survey{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public String getShortname(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getShortname"))
;  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


}