package com.ec.survey.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.ec.survey.Interface.PDFService;
public class PDFServiceImpl implements PDFService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://6";


public java.io.File createAnswerPDF(Integer answerSetId,String uniqueCode,String surveyUid,boolean isDraft){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/createAnswerPDF"))
    .queryParam("answerSetId",answerSetId)
    .queryParam("uniqueCode",uniqueCode)
    .queryParam("surveyUid",surveyUid)
    .queryParam("isDraft",isDraft)
;  java.io.File aux = restTemplate.getForObject(builder.toUriString(), java.io.File.class);

 return aux;
}


}