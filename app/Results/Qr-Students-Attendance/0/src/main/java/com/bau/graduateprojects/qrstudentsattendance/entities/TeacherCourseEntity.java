package com.bau.graduateprojects.qrstudentsattendance.entities;
 import lombok.Data;
import javax.persistence;
import javax.validation.constraints.NotNull;
@Entity
@Data
@Table(name = "TEACHER_COURSE")
public class TeacherCourseEntity {

@Id
@GeneratedValue(strategy = GenerationType.SEQUENCE)
 private  Long id;

@NotNull
 private  Long teacherId;

@NotNull
 private  Long courseId;


}