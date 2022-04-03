package com.ec.survey.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.ec.survey.Interface.PDFService;
public class PDFServiceImpl implements PDFService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://6";


public java.io.File createSurveyPDF(Survey survey,String lang,java.io.File target){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/createSurveyPDF"))
    .queryParam("survey",survey)
    .queryParam("lang",lang)
    .queryParam("target",target)
;  java.io.File aux = restTemplate.getForObject(builder.toUriString(), java.io.File.class);

 return aux;
}


}