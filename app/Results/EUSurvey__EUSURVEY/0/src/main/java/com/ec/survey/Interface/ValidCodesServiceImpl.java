package com.ec.survey.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.ec.survey.Interface.ValidCodesService;
public class ValidCodesServiceImpl implements ValidCodesService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://8";


public boolean checkValid(String uniqueCode,String surveyUid){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/checkValid"))
    .queryParam("uniqueCode",uniqueCode)
    .queryParam("surveyUid",surveyUid)
;  boolean aux = restTemplate.getForObject(builder.toUriString(), boolean.class);

 return aux;
}


}