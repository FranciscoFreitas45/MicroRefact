package com.hmm.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.hmm.Interface.SchedulEventService;
public class SchedulEventServiceImpl implements SchedulEventService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://7";


public float findattenceTotalTime(String userbname){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findattenceTotalTime"))
    .queryParam("userbname",userbname)
;  float aux = restTemplate.getForObject(builder.toUriString(), float.class);

 return aux;
}


public int findWorkTotalDay(String username){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findWorkTotalDay"))
    .queryParam("username",username)
;  int aux = restTemplate.getForObject(builder.toUriString(), int.class);

 return aux;
}


public Integer findTotalPerson(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findTotalPerson"))
;  Integer aux = restTemplate.getForObject(builder.toUriString(), Integer.class);

 return aux;
}


}