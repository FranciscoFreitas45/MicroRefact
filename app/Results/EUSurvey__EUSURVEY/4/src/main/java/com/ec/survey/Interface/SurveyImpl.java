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


public Integer getId(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getId"))
;  Integer aux = restTemplate.getForObject(builder.toUriString(), Integer.class);

 return aux;
}


public Publication getPublication(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getPublication"))
;  Publication aux = restTemplate.getForObject(builder.toUriString(), Publication.class);

 return aux;
}


public String getUniqueId(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getUniqueId"))
;  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


public String cleanTitle(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/cleanTitle"))
;  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


public String cleanTitleForMailSubject(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/cleanTitleForMailSubject"))
;  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


}