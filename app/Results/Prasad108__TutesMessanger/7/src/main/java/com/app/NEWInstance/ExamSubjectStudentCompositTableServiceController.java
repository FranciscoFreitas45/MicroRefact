package com.app.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ExamSubjectStudentCompositTableServiceController {

 private ExamSubjectStudentCompositTableService examsubjectstudentcomposittableservice;


@GetMapping
("/findByExamId")
public List<Student> findByExamId(@RequestParam(name = "examId") int examId,@RequestParam(name = "subDivId") int subDivId){
  return examsubjectstudentcomposittableservice.findByExamId(examId,subDivId);
}


@GetMapping
("/findByExamSubDivId")
public ExamSubjectStudentCompositTable findByExamSubDivId(@RequestParam(name = "examId") int examId,@RequestParam(name = "subDivId") int subDivId){
  return examsubjectstudentcomposittableservice.findByExamSubDivId(examId,subDivId);
}


@PutMapping
("/create")
public void create(@RequestParam(name = "examSubjectStudentComp") ExamSubjectStudentCompositTable examSubjectStudentComp){
examsubjectstudentcomposittableservice.create(examSubjectStudentComp);
}


@PutMapping
("/deletStudentFromExam")
public void deletStudentFromExam(@RequestParam(name = "StudId") int StudId,@RequestParam(name = "subDivId") int subDivId,@RequestParam(name = "examId") int examId){
examsubjectstudentcomposittableservice.deletStudentFromExam(StudId,subDivId,examId);
}


@GetMapping
("/examSubjectStudentResult")
public String examSubjectStudentResult(@RequestParam(name = "ExamId") int ExamId,@RequestParam(name = "SubdivId") int SubdivId){
  return examsubjectstudentcomposittableservice.examSubjectStudentResult(ExamId,SubdivId);
}


@PutMapping
("/deletSubjectFromExam")
public void deletSubjectFromExam(@RequestParam(name = "ExamId") int ExamId,@RequestParam(name = "SubdivId") int SubdivId){
examsubjectstudentcomposittableservice.deletSubjectFromExam(ExamId,SubdivId);
}


}