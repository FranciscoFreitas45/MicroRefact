package com.bau.graduateprojects.qrstudentsattendance.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.bau.graduateprojects.qrstudentsattendance.Interface.CourseRepository;
public class CourseRepositoryImpl implements CourseRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


public boolean existById(Long courseId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/existById"))
    .queryParam("courseId",courseId)
;  boolean aux = restTemplate.getForObject(builder.toUriString(), boolean.class);

 return aux;
}


public CourseEntity getById(Long id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getById"))
    .queryParam("id",id)
;  CourseEntity aux = restTemplate.getForObject(builder.toUriString(), CourseEntity.class);

 return aux;
}


public CourseEntity update(CourseEntity courseEntity){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/update"))
    .queryParam("courseEntity",courseEntity)
;  CourseEntity aux = restTemplate.getForObject(builder.toUriString(), CourseEntity.class);

 return aux;
}


}