package com.app.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.app.Interface.StudentService;
public class StudentServiceImpl implements StudentService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


public Student findByLoginId(int id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByLoginId"))
    .queryParam("id",id)
;  Student aux = restTemplate.getForObject(builder.toUriString(), Student.class);

 return aux;
}


public Institute GetInstitute(int id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/GetInstitute"))
    .queryParam("id",id)
;  Institute aux = restTemplate.getForObject(builder.toUriString(), Institute.class);

 return aux;
}


}