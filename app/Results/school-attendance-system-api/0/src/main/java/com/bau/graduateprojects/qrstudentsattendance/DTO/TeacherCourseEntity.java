package com.bau.graduateprojects.qrstudentsattendance.DTO;
 import lombok.Data;
import javax.persistence;
import javax.validation.constraints.NotNull;
public class TeacherCourseEntity {

 private  Long id;

 private  Long teacherId;

 private  Long courseId;


}