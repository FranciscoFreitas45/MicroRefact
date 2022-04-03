package com.bau.graduateprojects.qrstudentsattendance.servicies.integration;
 import com.bau.graduateprojects.qrstudentsattendance.entities;
import java.util.List;
public interface IntegrationService {


public TeacherCourseEntity addCourseToTeacher(TeacherCourseEntity entity)
;

public List<AttendanceEntity> getAllAttendanceByLectureId(Long lId)
;

public List<LectureEntity> getAllLecturesByCourseId(Long cId)
;

public StudentCourseEntity addCourseToStudent(StudentCourseEntity record)
;

public List<CourseEntity> getAllCoursesByStudentId(Long sId)
;

public LectureCourseEntity addLectureToCourse(LectureCourseEntity entity)
;

public LectureAttendanceEntity addAttendanceToLecture(LectureAttendanceEntity entity)
;

public AttendanceEntity updateStatusAttendanceId(Long attId)
;

public List<CourseEntity> getAllCoursesByTeacherId(Long tId)
;

public List<StudentEntity> getAllStudentsByCourseId(Long cId)
;

public void removeLectureFromCourse(Long lId,Long cId)
;

public void removeCourseFromTeacher(Long tId,Long cId)
;

public void removeCourseFromStudent(Long sId,Long cId)
;

}