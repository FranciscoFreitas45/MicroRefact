package com.bau.graduateprojects.qrstudentsattendance.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.bau.graduateprojects.qrstudentsattendance.Interface.LectureAttendanceRepository;
public class LectureAttendanceRepositoryImpl implements LectureAttendanceRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://6";


public List<AttendanceEntity> getAllAttendanceByLectureId(Long lId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getAllAttendanceByLectureId"))
    .queryParam("lId",lId)
;  List<AttendanceEntity> aux = restTemplate.getForObject(builder.toUriString(), List<AttendanceEntity>.class);

 return aux;
}


public LectureAttendanceEntity addAttendanceToLecture(LectureAttendanceEntity entity){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/addAttendanceToLecture"))
    .queryParam("entity",entity)
;  LectureAttendanceEntity aux = restTemplate.getForObject(builder.toUriString(), LectureAttendanceEntity.class);

 return aux;
}


public AttendanceEntity updateStatusAttendanceId(Long attId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/updateStatusAttendanceId"))
    .queryParam("attId",attId)
;  AttendanceEntity aux = restTemplate.getForObject(builder.toUriString(), AttendanceEntity.class);

 return aux;
}


}