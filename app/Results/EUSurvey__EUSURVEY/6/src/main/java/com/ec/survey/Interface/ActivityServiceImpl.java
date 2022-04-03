package com.ec.survey.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.ec.survey.Interface.ActivityService;
public class ActivityServiceImpl implements ActivityService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


public boolean isLogEnabled(int activityCode){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isLogEnabled"))
    .queryParam("activityCode",activityCode)
;  boolean aux = restTemplate.getForObject(builder.toUriString(), boolean.class);

 return aux;
}


public void log(int activityCode,String oldValue,String newValue,int userId,String surveyUID){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/log"))
    .queryParam("activityCode",activityCode)
    .queryParam("oldValue",oldValue)
    .queryParam("newValue",newValue)
    .queryParam("userId",userId)
    .queryParam("surveyUID",surveyUID)
;
  restTemplate.put(builder.toUriString(), null);
}


}