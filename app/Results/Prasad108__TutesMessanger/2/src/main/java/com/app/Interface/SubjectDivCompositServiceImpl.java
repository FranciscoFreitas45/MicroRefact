package com.app.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.app.Interface.SubjectDivCompositService;
public class SubjectDivCompositServiceImpl implements SubjectDivCompositService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://9";


public void deleteByDivId(int subId,int divId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/deleteByDivId"))
    .queryParam("subId",subId)
    .queryParam("divId",divId)
;
  restTemplate.put(builder.toUriString(), null);
}


public List<Subject> findByDivId(int divId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByDivId"))
    .queryParam("divId",divId)
;  List<Subject> aux = restTemplate.getForObject(builder.toUriString(), List<Subject>.class);

 return aux;
}


public void create(SubjectDivComposit subDivComp){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/create"))
    .queryParam("subDivComp",subDivComp)
;
  restTemplate.put(builder.toUriString(), null);
}


public String findSubjectName(int subDivCompId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findSubjectName"))
    .queryParam("subDivCompId",subDivCompId)
;  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


}