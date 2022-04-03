package com.app.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ResultServiceController {

 private ResultService resultservice;


@GetMapping
("/smsSubjectStudentResult")
public String smsSubjectStudentResult(@RequestParam(name = "ExamId") int ExamId,@RequestParam(name = "SubdivId") int SubdivId){
  return resultservice.smsSubjectStudentResult(ExamId,SubdivId);
}


@GetMapping
("/updateResult")
public int updateResult(@RequestParam(name = "StudResultList") List<HashMap<String,String>> StudResultList){
  return resultservice.updateResult(StudResultList);
}


}