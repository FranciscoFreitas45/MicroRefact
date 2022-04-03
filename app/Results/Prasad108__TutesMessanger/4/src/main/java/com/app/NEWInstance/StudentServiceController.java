package com.app.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class StudentServiceController {

 private StudentService studentservice;


@GetMapping
("/findByLoginId")
public Student findByLoginId(@RequestParam(name = "id") int id){
  return studentservice.findByLoginId(id);
}


@GetMapping
("/GetInstitute")
public Institute GetInstitute(@RequestParam(name = "id") int id){
  return studentservice.GetInstitute(id);
}


@GetMapping
("/find")
public Student find(@RequestParam(name = "id") int id){
  return studentservice.find(id);
}


@GetMapping
("/findByDivId")
public List<Student> findByDivId(@RequestParam(name = "id") int id){
  return studentservice.findByDivId(id);
}


@PutMapping
("/deleteSelectedFromDiv")
public void deleteSelectedFromDiv(@RequestParam(name = "id") int id){
studentservice.deleteSelectedFromDiv(id);
}


@PutMapping
("/SetDivisionId")
public void SetDivisionId(@RequestParam(name = "StudentId") int StudentId,@RequestParam(name = "DiviID") int DiviID){
studentservice.SetDivisionId(StudentId,DiviID);
}


}