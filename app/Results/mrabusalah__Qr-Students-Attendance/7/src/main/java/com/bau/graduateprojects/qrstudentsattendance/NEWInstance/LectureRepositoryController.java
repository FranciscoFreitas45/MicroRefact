package com.bau.graduateprojects.qrstudentsattendance.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class LectureRepositoryController {

 private LectureRepository lecturerepository;


@GetMapping
("/existById")
public boolean existById(@RequestParam(name = "lectureId") Long lectureId){
  return lecturerepository.existById(lectureId);
}


@GetMapping
("/getById")
public LectureEntity getById(@RequestParam(name = "id") Long id){
  return lecturerepository.getById(id);
}


}