package com.app.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.app.Interface.TeacherService;
public class TeacherServiceImpl implements TeacherService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public Institute GetInstitute(int id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/GetInstitute"))
    .queryParam("id",id)
;  Institute aux = restTemplate.getForObject(builder.toUriString(), Institute.class);

 return aux;
}


public Teacher find(int id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/find"))
    .queryParam("id",id)
;  Teacher aux = restTemplate.getForObject(builder.toUriString(), Teacher.class);

 return aux;
}


public void update(Teacher teacher){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/update"))
    .queryParam("teacher",teacher)
;
  restTemplate.put(builder.toUriString(), null);
}


public void changeUserName(String newUserName,Login login){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/changeUserName"))
    .queryParam("newUserName",newUserName)
    .queryParam("login",login)
;
  restTemplate.put(builder.toUriString(), null);
}


public void changePassword(String newPassword,Login login){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/changePassword"))
    .queryParam("newPassword",newPassword)
    .queryParam("login",login)
;
  restTemplate.put(builder.toUriString(), null);
}


}