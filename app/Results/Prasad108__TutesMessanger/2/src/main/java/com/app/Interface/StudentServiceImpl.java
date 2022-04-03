package com.app.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.app.Interface.StudentService;
public class StudentServiceImpl implements StudentService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


public Student find(int id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/find"))
    .queryParam("id",id)
;  Student aux = restTemplate.getForObject(builder.toUriString(), Student.class);

 return aux;
}


public List<Student> findByDivId(int id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByDivId"))
    .queryParam("id",id)
;  List<Student> aux = restTemplate.getForObject(builder.toUriString(), List<Student>.class);

 return aux;
}


public void deleteSelectedFromDiv(int id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/deleteSelectedFromDiv"))
    .queryParam("id",id)
;
  restTemplate.put(builder.toUriString(), null);
}


public void SetDivisionId(int StudentId,int DiviID){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/SetDivisionId"))
    .queryParam("StudentId",StudentId)
    .queryParam("DiviID",DiviID)
;
  restTemplate.put(builder.toUriString(), null);
}


}