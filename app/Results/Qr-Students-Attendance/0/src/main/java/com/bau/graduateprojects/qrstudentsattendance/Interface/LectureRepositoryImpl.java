package com.bau.graduateprojects.qrstudentsattendance.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.bau.graduateprojects.qrstudentsattendance.Interface.LectureRepository;
public class LectureRepositoryImpl implements LectureRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://8";


public boolean existById(Long lectureId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/existById"))
    .queryParam("lectureId",lectureId)
;  boolean aux = restTemplate.getForObject(builder.toUriString(), boolean.class);

 return aux;
}


public LectureEntity getById(Long id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getById"))
    .queryParam("id",id)
;  LectureEntity aux = restTemplate.getForObject(builder.toUriString(), LectureEntity.class);

 return aux;
}


}