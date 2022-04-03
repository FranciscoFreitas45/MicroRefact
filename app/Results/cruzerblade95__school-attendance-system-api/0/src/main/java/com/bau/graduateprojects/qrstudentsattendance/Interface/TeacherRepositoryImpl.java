package com.bau.graduateprojects.qrstudentsattendance.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.bau.graduateprojects.qrstudentsattendance.Interface.TeacherRepository;
public class TeacherRepositoryImpl implements TeacherRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public boolean existById(Long teacherId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/existById"))
    .queryParam("teacherId",teacherId)
;  boolean aux = restTemplate.getForObject(builder.toUriString(), boolean.class);

 return aux;
}


public TeacherEntity getById(Long id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getById"))
    .queryParam("id",id)
;  TeacherEntity aux = restTemplate.getForObject(builder.toUriString(), TeacherEntity.class);

 return aux;
}


}