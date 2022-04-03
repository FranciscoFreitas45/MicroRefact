package com.app.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.app.Interface.ScheduleService;
public class ScheduleServiceImpl implements ScheduleService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


public Schedule fordivision(int divID){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/fordivision"))
    .queryParam("divID",divID)
;  Schedule aux = restTemplate.getForObject(builder.toUriString(), Schedule.class);

 return aux;
}


public void create(Schedule schedule){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/create"))
    .queryParam("schedule",schedule)
;
  restTemplate.put(builder.toUriString(), null);
}


public void update(Schedule schedule){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/update"))
    .queryParam("schedule",schedule)
;
  restTemplate.put(builder.toUriString(), null);
}


}