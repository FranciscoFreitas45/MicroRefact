package com.bau.graduateprojects.qrstudentsattendance.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.bau.graduateprojects.qrstudentsattendance.Interface.TeacherCourseRepository;
public class TeacherCourseRepositoryImpl implements TeacherCourseRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


public boolean exist(Long teacherId,Long courseId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/exist"))
    .queryParam("teacherId",teacherId)
    .queryParam("courseId",courseId)
;  boolean aux = restTemplate.getForObject(builder.toUriString(), boolean.class);

 return aux;
}


public TeacherCourseEntity insert(TeacherCourseEntity entity){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/insert"))
    .queryParam("entity",entity)
;  TeacherCourseEntity aux = restTemplate.getForObject(builder.toUriString(), TeacherCourseEntity.class);

 return aux;
}


public TeacherCourseEntity getByTeacherIdAndCourseId(Long tId,Long cId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getByTeacherIdAndCourseId"))
    .queryParam("tId",tId)
    .queryParam("cId",cId)
;  TeacherCourseEntity aux = restTemplate.getForObject(builder.toUriString(), TeacherCourseEntity.class);

 return aux;
}


public void removeById(Long id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/removeById"))
    .queryParam("id",id)
;
  restTemplate.put(builder.toUriString(), null);
}


public List<TeacherCourseEntity> getAllCoursesByTeacherId(Long tId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getAllCoursesByTeacherId"))
    .queryParam("tId",tId)
;  List<TeacherCourseEntity> aux = restTemplate.getForObject(builder.toUriString(), List<TeacherCourseEntity>.class);

 return aux;
}


}