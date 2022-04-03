package com.bau.graduateprojects.qrstudentsattendance.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.bau.graduateprojects.qrstudentsattendance.Interface.StudentCourseRepository;
public class StudentCourseRepositoryImpl implements StudentCourseRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


public boolean exist(Long studentId,Long courseId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/exist"))
    .queryParam("studentId",studentId)
    .queryParam("courseId",courseId)
;  boolean aux = restTemplate.getForObject(builder.toUriString(), boolean.class);

 return aux;
}


public StudentCourseEntity insert(StudentCourseEntity entity){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/insert"))
    .queryParam("entity",entity)
;  StudentCourseEntity aux = restTemplate.getForObject(builder.toUriString(), StudentCourseEntity.class);

 return aux;
}


public StudentCourseEntity getByStudentIdAndCourseId(Long sId,Long cId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getByStudentIdAndCourseId"))
    .queryParam("sId",sId)
    .queryParam("cId",cId)
;  StudentCourseEntity aux = restTemplate.getForObject(builder.toUriString(), StudentCourseEntity.class);

 return aux;
}


public void removeById(Long id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/removeById"))
    .queryParam("id",id)
;
  restTemplate.put(builder.toUriString(), null);
}


public List<StudentCourseEntity> getAllCoursesByStudentId(Long sId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getAllCoursesByStudentId"))
    .queryParam("sId",sId)
;  List<StudentCourseEntity> aux = restTemplate.getForObject(builder.toUriString(), List<StudentCourseEntity>.class);

 return aux;
}


public List<StudentCourseEntity> getAllStudentsByCourseId(Long cId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getAllStudentsByCourseId"))
    .queryParam("cId",cId)
;  List<StudentCourseEntity> aux = restTemplate.getForObject(builder.toUriString(), List<StudentCourseEntity>.class);

 return aux;
}


}