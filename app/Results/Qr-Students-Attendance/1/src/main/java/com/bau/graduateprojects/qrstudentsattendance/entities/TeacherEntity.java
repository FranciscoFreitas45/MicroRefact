package com.bau.graduateprojects.qrstudentsattendance.entities;
 import lombok.Data;
import javax.persistence;
import javax.validation.constraints.NotBlank;
@Entity
@Data
@Table(name = "TEACHER")
public class TeacherEntity {

@Id
@GeneratedValue(strategy = GenerationType.SEQUENCE)
 private  Long id;

@NotBlank(message = "name is required")
 private  String name;

@NotBlank(message = "username is required")
 private  String username;

@NotBlank(message = "password id required")
 private  String password;

 private  String phone;

 private  String email;

 private  String officeLocation;

 private  String officeHoursFrom;

 private  String officeHoursTo;

 private  String officeHoursDays;


}