package com.ec.survey.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.ec.survey.Interface.ResultFilter;
public class ResultFilterImpl implements ResultFilter{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


public String getHash(boolean allAnswers){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getHash"))
    .queryParam("allAnswers",allAnswers)
;  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


}