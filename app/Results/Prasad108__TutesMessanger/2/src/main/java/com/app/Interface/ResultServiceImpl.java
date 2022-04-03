package com.app.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.app.Interface.ResultService;
public class ResultServiceImpl implements ResultService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://7";


public String smsSubjectStudentResult(int ExamId,int SubdivId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/smsSubjectStudentResult"))
    .queryParam("ExamId",ExamId)
    .queryParam("SubdivId",SubdivId)
;  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


public int updateResult(List<HashMap<String,String>> StudResultList){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/updateResult"))
    .queryParam("StudResultList",StudResultList)
;  int aux = restTemplate.getForObject(builder.toUriString(), int.class);

 return aux;
}


}