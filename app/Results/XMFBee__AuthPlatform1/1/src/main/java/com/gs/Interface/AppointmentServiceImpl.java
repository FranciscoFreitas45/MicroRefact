package com.gs.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.gs.Interface.AppointmentService;
public class AppointmentServiceImpl implements AppointmentService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://9";


public List<Appointment> queryByCompanyId(String companyId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/queryByCompanyId"))
    .queryParam("companyId",companyId)
;  List<Appointment> aux = restTemplate.getForObject(builder.toUriString(), List<Appointment>.class);

 return aux;
}


}