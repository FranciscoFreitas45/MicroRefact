package com.app.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class InstituteServiceController {

 private InstituteService instituteservice;


@GetMapping
("/find")
public Institute find(@RequestParam(name = "name") String name){
  return instituteservice.find(name);
}


@GetMapping
("/getallPendingTeacherForApproval")
public List<Teacher> getallPendingTeacherForApproval(@RequestParam(name = "institute") Institute institute){
  return instituteservice.getallPendingTeacherForApproval(institute);
}


@GetMapping
("/getallPendingStudentForApproval")
public List<Student> getallPendingStudentForApproval(@RequestParam(name = "institute") Institute institute){
  return instituteservice.getallPendingStudentForApproval(institute);
}


@GetMapping
("/getallStudentWhoAreNotInAnyDivision")
public List<Student> getallStudentWhoAreNotInAnyDivision(@RequestParam(name = "institute") Institute institute){
  return instituteservice.getallStudentWhoAreNotInAnyDivision(institute);
}


@GetMapping
("/GetSubjectTree")
public String GetSubjectTree(@RequestParam(name = "InstId") int InstId){
  return instituteservice.GetSubjectTree(InstId);
}


@GetMapping
("/GetInstituteTree")
public String GetInstituteTree(@RequestParam(name = "InstId") int InstId){
  return instituteservice.GetInstituteTree(InstId);
}


@GetMapping
("/TreeStructureSujectsNotInExam")
public String TreeStructureSujectsNotInExam(@RequestParam(name = "InstId") int InstId,@RequestParam(name = "ExamId") int ExamId){
  return instituteservice.TreeStructureSujectsNotInExam(InstId,ExamId);
}


@GetMapping
("/TreeStructureSujectsOFExam")
public String TreeStructureSujectsOFExam(@RequestParam(name = "InstId") int InstId,@RequestParam(name = "ExamId") int ExamId){
  return instituteservice.TreeStructureSujectsOFExam(InstId,ExamId);
}


@GetMapping
("/getall")
public List<Institute> getall(){
  return instituteservice.getall();
}


}