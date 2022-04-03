package com.bau.graduateprojects.qrstudentsattendance.entities;
 import lombok.Data;
import javax.persistence;
import javax.validation.constraints.NotNull;
@Entity
@Data
@Table(name = "STUDENT_COURSE")
public class StudentCourseEntity {

@Id
@GeneratedValue(strategy = GenerationType.SEQUENCE)
 private  Long id;

@NotNull
 private  Long studentId;

@NotNull
 private  Long courseId;


}