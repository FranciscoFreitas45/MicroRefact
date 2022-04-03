package com.gs.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.gs.Interface.AppointmentService;
public class AppointmentServiceImpl implements AppointmentService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://9";


public List<Appointment> queryByPhone(String userPhone){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/queryByPhone"))
    .queryParam("userPhone",userPhone)
;  List<Appointment> aux = restTemplate.getForObject(builder.toUriString(), List<Appointment>.class);

 return aux;
}


public void updateUserIds(String userId,String appIds){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/updateUserIds"))
    .queryParam("userId",userId)
    .queryParam("appIds",appIds)
;
  restTemplate.put(builder.toUriString(), null);
}


}