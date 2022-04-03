package com.bau.graduateprojects.qrstudentsattendance.controllers;
 import com.bau.graduateprojects.qrstudentsattendance.entities;
import com.bau.graduateprojects.qrstudentsattendance.servicies.integration.IntegrationService;
import org.springframework.web.bind.annotation;
import java.util.List;
@RestController
@RequestMapping("/api/v1/integration")
public class IntegrationController {

 private  IntegrationService integrationService;

public IntegrationController(IntegrationService integrationService) {
    this.integrationService = integrationService;
}
@PostMapping("/course-teacher")
public TeacherCourseEntity addCourseToTeacher(TeacherCourseEntity entity){
    return integrationService.addCourseToTeacher(entity);
}


@GetMapping("/lecture-attendance/lectures/{lId}")
public List<AttendanceEntity> getAllAttendanceByLectureId(Long lId){
    return integrationService.getAllAttendanceByLectureId(lId);
}


@GetMapping("/lecture-course/lectures/{cId}")
public List<LectureEntity> getAllLecturesByCourseId(Long cId){
    return integrationService.getAllLecturesByCourseId(cId);
}


@PostMapping("/course-student")
public StudentCourseEntity addCourseToStudent(StudentCourseEntity entity){
    return integrationService.addCourseToStudent(entity);
}


@GetMapping("/course-student/courses/{sId}")
public List<CourseEntity> getAllCoursesByStudentId(Long sId){
    return integrationService.getAllCoursesByStudentId(sId);
}


@PostMapping("/lecture-course")
public LectureCourseEntity addLectureToCourse(LectureCourseEntity entity){
    return integrationService.addLectureToCourse(entity);
}


@PostMapping("/lecture-attendance")
public LectureAttendanceEntity addAttendanceToLecture(LectureAttendanceEntity entity){
    return integrationService.addAttendanceToLecture(entity);
}


@PutMapping("/lecture-attendance/update/{attId}")
public AttendanceEntity updateStatusAttendanceId(Long attId){
    return integrationService.updateStatusAttendanceId(attId);
}


@GetMapping("/course-teacher/courses/{tId}")
public List<CourseEntity> getAllCoursesByTeacherId(Long tId){
    return integrationService.getAllCoursesByTeacherId(tId);
}


@GetMapping("/course-student/students/{cId}")
public List<StudentEntity> getAllStudentsByCourseId(Long cId){
    return integrationService.getAllStudentsByCourseId(cId);
}


@DeleteMapping("/lecture-course/{lId}/{cId}")
public void removeLectureFromCourse(Long lId,Long cId){
    integrationService.removeLectureFromCourse(lId, cId);
}


@DeleteMapping("/course-teacher/{tId}/{cId}")
public void removeCourseFromTeacher(Long tId,Long cId){
    integrationService.removeCourseFromTeacher(tId, cId);
}


@DeleteMapping("/course-student/{sId}/{cId}")
public void removeCourseFromStudent(Long sId,Long cId){
    integrationService.removeCourseFromStudent(sId, cId);
}


}