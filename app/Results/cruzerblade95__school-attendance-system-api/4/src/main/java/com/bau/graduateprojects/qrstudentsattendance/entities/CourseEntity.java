package com.bau.graduateprojects.qrstudentsattendance.entities;
 import lombok.Data;
import javax.persistence;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
@Entity
@Data
@Table(name = "COURSE")
public class CourseEntity {

@Id
@GeneratedValue(strategy = GenerationType.SEQUENCE)
 private  Long id;

@NotBlank(message = "name is required")
 private  String name;

@NotBlank(message = "hour from is required")
 private  String hourFrom;

@NotBlank(message = "hour to is required")
 private  String hourTo;

@NotBlank(message = "week days is required")
 private  String days;

@NotNull(message = "hours is required")
 private  int hours;

@NotNull(message = "section number is required")
 private  int sectionNumber;

 private  String teacherName;


}