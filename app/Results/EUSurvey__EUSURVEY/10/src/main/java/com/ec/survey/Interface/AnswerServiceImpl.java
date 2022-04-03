package com.ec.survey.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.ec.survey.Interface.AnswerService;
public class AnswerServiceImpl implements AnswerService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public List<AnswerSet> getAllAnswers(int surveyId,ResultFilter filter){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getAllAnswers"))
    .queryParam("surveyId",surveyId)
    .queryParam("filter",filter)
;  List<AnswerSet> aux = restTemplate.getForObject(builder.toUriString(), List<AnswerSet>.class);

 return aux;
}


public void save(Statistics s){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/save"))
    .queryParam("s",s)
;
  restTemplate.put(builder.toUriString(), null);
}


public String getSql(String prefix,int surveyId,ResultFilter filter,Map<String,Object> values,boolean searchallsurveys){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getSql"))
    .queryParam("prefix",prefix)
    .queryParam("surveyId",surveyId)
    .queryParam("filter",filter)
    .queryParam("values",values)
    .queryParam("searchallsurveys",searchallsurveys)
;  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


}