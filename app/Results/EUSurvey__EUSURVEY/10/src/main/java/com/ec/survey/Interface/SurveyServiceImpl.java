package com.ec.survey.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.ec.survey.Interface.SurveyService;
public class SurveyServiceImpl implements SurveyService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public void initializeSurvey(Survey survey){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/initializeSurvey"))
    .queryParam("survey",survey)
;
  restTemplate.put(builder.toUriString(), null);
}


public void checkAndRecreateMissingElements(Survey survey,ResultFilter filter){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/checkAndRecreateMissingElements"))
    .queryParam("survey",survey)
    .queryParam("filter",filter)
;
  restTemplate.put(builder.toUriString(), null);
}


public Map<Integer,String> getUniqueIdsById(Survey publishedSurvey){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getUniqueIdsById"))
    .queryParam("publishedSurvey",publishedSurvey)
;  Map<Integer,String> aux = restTemplate.getForObject(builder.toUriString(), Map<Integer,String>.class);

 return aux;
}


}