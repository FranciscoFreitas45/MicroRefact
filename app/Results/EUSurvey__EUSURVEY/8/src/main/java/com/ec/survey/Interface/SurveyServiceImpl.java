package com.ec.survey.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.ec.survey.Interface.SurveyService;
public class SurveyServiceImpl implements SurveyService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public Survey editSave(Survey oldsurvey,HttpServletRequest request){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/editSave"))
    .queryParam("oldsurvey",oldsurvey)
    .queryParam("request",request)
;  Survey aux = restTemplate.getForObject(builder.toUriString(), Survey.class);

 return aux;
}


public boolean answerSetExists(String uniqueCode,boolean isDraft,boolean addErrorIfExists){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/answerSetExists"))
    .queryParam("uniqueCode",uniqueCode)
    .queryParam("isDraft",isDraft)
    .queryParam("addErrorIfExists",addErrorIfExists)
;  boolean aux = restTemplate.getForObject(builder.toUriString(), boolean.class);

 return aux;
}


public void removeFromSessionCache(Survey survey){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/removeFromSessionCache"))
    .queryParam("survey",survey)
;
  restTemplate.put(builder.toUriString(), null);
}


}