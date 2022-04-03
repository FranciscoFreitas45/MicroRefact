package com.app.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.app.Interface.SubjectService;
public class SubjectServiceImpl implements SubjectService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://9";


public Subject find(int id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/find"))
    .queryParam("id",id)
;  Subject aux = restTemplate.getForObject(builder.toUriString(), Subject.class);

 return aux;
}


public List<Subject> getallOfInstitute(int instituteId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getallOfInstitute"))
    .queryParam("instituteId",instituteId)
;  List<Subject> aux = restTemplate.getForObject(builder.toUriString(), List<Subject>.class);

 return aux;
}


public void deleteFromInstitute(int subId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/deleteFromInstitute"))
    .queryParam("subId",subId)
;
  restTemplate.put(builder.toUriString(), null);
}


public void create(Subject subject){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/create"))
    .queryParam("subject",subject)
;
  restTemplate.put(builder.toUriString(), null);
}


public void update(Subject subject){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/update"))
    .queryParam("subject",subject)
;
  restTemplate.put(builder.toUriString(), null);
}


}