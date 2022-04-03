package com.gs.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.gs.Interface.AppointmentService;
public class AppointmentServiceImpl implements AppointmentService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://9";


public void updateCurrentById(String currentStatus,String appointmentId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/updateCurrentById"))
    .queryParam("currentStatus",currentStatus)
    .queryParam("appointmentId",appointmentId)
;
  restTemplate.put(builder.toUriString(), null);
}


}