package com.app.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.app.Interface.ExamSubjectStudentCompositTableService;
public class ExamSubjectStudentCompositTableServiceImpl implements ExamSubjectStudentCompositTableService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://7";


public List<Student> findByExamId(int examId,int subDivId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByExamId"))
    .queryParam("examId",examId)
    .queryParam("subDivId",subDivId)
;  List<Student> aux = restTemplate.getForObject(builder.toUriString(), List<Student>.class);

 return aux;
}


public ExamSubjectStudentCompositTable findByExamSubDivId(int examId,int subDivId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByExamSubDivId"))
    .queryParam("examId",examId)
    .queryParam("subDivId",subDivId)
;  ExamSubjectStudentCompositTable aux = restTemplate.getForObject(builder.toUriString(), ExamSubjectStudentCompositTable.class);

 return aux;
}


public void create(ExamSubjectStudentCompositTable examSubjectStudentComp){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/create"))
    .queryParam("examSubjectStudentComp",examSubjectStudentComp)
;
  restTemplate.put(builder.toUriString(), null);
}


public void deletStudentFromExam(int StudId,int subDivId,int examId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/deletStudentFromExam"))
    .queryParam("StudId",StudId)
    .queryParam("subDivId",subDivId)
    .queryParam("examId",examId)
;
  restTemplate.put(builder.toUriString(), null);
}


public String examSubjectStudentResult(int ExamId,int SubdivId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/examSubjectStudentResult"))
    .queryParam("ExamId",ExamId)
    .queryParam("SubdivId",SubdivId)
;  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


public void deletSubjectFromExam(int ExamId,int SubdivId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/deletSubjectFromExam"))
    .queryParam("ExamId",ExamId)
    .queryParam("SubdivId",SubdivId)
;
  restTemplate.put(builder.toUriString(), null);
}


}