package com.bau.graduateprojects.qrstudentsattendance.DTO;
 import lombok.Data;
import javax.persistence;
import javax.validation.constraints.NotNull;
public class StudentCourseEntity {

 private  Long id;

 private  Long studentId;

 private  Long courseId;


}