package com.bau.graduateprojects.qrstudentsattendance.DTO;
 import lombok.Data;
import javax.persistence;
import javax.validation.constraints.NotNull;
public class AttendanceEntity {

 private  Long id;

 private  String status;

 private  String studentUsername;


}