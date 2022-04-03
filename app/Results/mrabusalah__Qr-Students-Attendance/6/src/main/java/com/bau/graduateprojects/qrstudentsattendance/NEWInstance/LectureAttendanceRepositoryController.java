package com.bau.graduateprojects.qrstudentsattendance.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class LectureAttendanceRepositoryController {

 private LectureAttendanceRepository lectureattendancerepository;


@GetMapping
("/getAllAttendanceByLectureId")
public List<AttendanceEntity> getAllAttendanceByLectureId(@RequestParam(name = "lId") Long lId){
  return lectureattendancerepository.getAllAttendanceByLectureId(lId);
}


@GetMapping
("/addAttendanceToLecture")
public LectureAttendanceEntity addAttendanceToLecture(@RequestParam(name = "entity") LectureAttendanceEntity entity){
  return lectureattendancerepository.addAttendanceToLecture(entity);
}


@GetMapping
("/updateStatusAttendanceId")
public AttendanceEntity updateStatusAttendanceId(@RequestParam(name = "attId") Long attId){
  return lectureattendancerepository.updateStatusAttendanceId(attId);
}


}