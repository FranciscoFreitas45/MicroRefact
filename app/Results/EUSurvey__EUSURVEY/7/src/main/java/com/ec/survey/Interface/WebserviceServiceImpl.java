package com.ec.survey.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.ec.survey.Interface.WebserviceService;
public class WebserviceServiceImpl implements WebserviceService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


public ServiceRequest getServiceRequest(Integer userId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getServiceRequest"))
    .queryParam("userId",userId)
;  ServiceRequest aux = restTemplate.getForObject(builder.toUriString(), ServiceRequest.class);

 return aux;
}


public void save(WebserviceTask task){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/save"))
    .queryParam("task",task)
;
  restTemplate.put(builder.toUriString(), null);
}


public boolean startTask(WebserviceTask task,Locale locale){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/startTask"))
    .queryParam("task",task)
    .queryParam("locale",locale)
;  boolean aux = restTemplate.getForObject(builder.toUriString(), boolean.class);

 return aux;
}


public void increaseServiceRequest(Integer userId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/increaseServiceRequest"))
    .queryParam("userId",userId)
;
  restTemplate.put(builder.toUriString(), null);
}


public int getWaitingTokens(ParticipationGroup group){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getWaitingTokens"))
    .queryParam("group",group)
;  int aux = restTemplate.getForObject(builder.toUriString(), int.class);

 return aux;
}


public WebserviceTask get(int id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/get"))
    .queryParam("id",id)
;  WebserviceTask aux = restTemplate.getForObject(builder.toUriString(), WebserviceTask.class);

 return aux;
}


}