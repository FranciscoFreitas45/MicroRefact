package org.live.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.live.DTO.Grade;
import org.live.Request.GradeRequest;
public class GradeRequestImpl implements GradeRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public void setGrade(Grade grade,String id2FF1){
 restTemplate.put("http://13/Member/{id}/Grade/setGrade",grade,id2FF1);
 return ;
}


public Grade getGrade(String id2FF1){
 Grade aux = restTemplate.getForObject("http://13/Member/{id}/Grade/getGrade",Grade.class,id2FF1);
return aux;
}


}