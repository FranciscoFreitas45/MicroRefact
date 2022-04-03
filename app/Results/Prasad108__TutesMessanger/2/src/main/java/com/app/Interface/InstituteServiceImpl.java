package com.app.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.app.Interface.InstituteService;
public class InstituteServiceImpl implements InstituteService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


public Institute find(String name){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/find"))
    .queryParam("name",name)
;  Institute aux = restTemplate.getForObject(builder.toUriString(), Institute.class);

 return aux;
}


public List<Teacher> getallPendingTeacherForApproval(Institute institute){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getallPendingTeacherForApproval"))
    .queryParam("institute",institute)
;  List<Teacher> aux = restTemplate.getForObject(builder.toUriString(), List<Teacher>.class);

 return aux;
}


public List<Student> getallPendingStudentForApproval(Institute institute){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getallPendingStudentForApproval"))
    .queryParam("institute",institute)
;  List<Student> aux = restTemplate.getForObject(builder.toUriString(), List<Student>.class);

 return aux;
}


public List<Student> getallStudentWhoAreNotInAnyDivision(Institute institute){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getallStudentWhoAreNotInAnyDivision"))
    .queryParam("institute",institute)
;  List<Student> aux = restTemplate.getForObject(builder.toUriString(), List<Student>.class);

 return aux;
}


public String GetSubjectTree(int InstId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/GetSubjectTree"))
    .queryParam("InstId",InstId)
;  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


public String GetInstituteTree(int InstId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/GetInstituteTree"))
    .queryParam("InstId",InstId)
;  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


public String TreeStructureSujectsNotInExam(int InstId,int ExamId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/TreeStructureSujectsNotInExam"))
    .queryParam("InstId",InstId)
    .queryParam("ExamId",ExamId)
;  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


public String TreeStructureSujectsOFExam(int InstId,int ExamId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/TreeStructureSujectsOFExam"))
    .queryParam("InstId",InstId)
    .queryParam("ExamId",ExamId)
;  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


}