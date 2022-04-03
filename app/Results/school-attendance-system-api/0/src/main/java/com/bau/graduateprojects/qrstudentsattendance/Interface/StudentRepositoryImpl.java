package com.bau.graduateprojects.qrstudentsattendance.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.bau.graduateprojects.qrstudentsattendance.Interface.StudentRepository;
public class StudentRepositoryImpl implements StudentRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


public boolean existById(Long studentId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/existById"))
    .queryParam("studentId",studentId)
;  boolean aux = restTemplate.getForObject(builder.toUriString(), boolean.class);

 return aux;
}


public StudentEntity getById(Long id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getById"))
    .queryParam("id",id)
;  StudentEntity aux = restTemplate.getForObject(builder.toUriString(), StudentEntity.class);

 return aux;
}


}