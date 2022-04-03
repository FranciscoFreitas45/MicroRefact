package com.bau.graduateprojects.qrstudentsattendance.controllers;
 import com.bau.graduateprojects.qrstudentsattendance.entities.LectureEntity;
import com.bau.graduateprojects.qrstudentsattendance.servicies.lecture.LectureService;
import org.springframework.web.bind.annotation;
import java.util.List;
@RestController
@RequestMapping("/api/v1/lecture")
public class LectureController {

 private  LectureService lectureService;

public LectureController(LectureService lectureService) {
    this.lectureService = lectureService;
}
@DeleteMapping("/{id}")
public void removeById(Long id){
    lectureService.removeById(id);
}


@GetMapping("/{id}")
public LectureEntity getById(Long id){
    return lectureService.getById(id);
}


@PostMapping("/{lId}")
public LectureEntity insert(LectureEntity lectureEntity,Long lId){
    return lectureService.insert(lectureEntity);
}


@GetMapping
public List<LectureEntity> list(){
    return lectureService.list();
}


}