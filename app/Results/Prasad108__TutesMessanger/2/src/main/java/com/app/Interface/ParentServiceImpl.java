package com.app.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.app.Interface.ParentService;
public class ParentServiceImpl implements ParentService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://6";


public Parent findByStudentId(int studId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByStudentId"))
    .queryParam("studId",studId)
;  Parent aux = restTemplate.getForObject(builder.toUriString(), Parent.class);

 return aux;
}


}