package com.bau.graduateprojects.qrstudentsattendance.entities;
 import lombok.Data;
import javax.persistence;
import javax.validation.constraints.NotNull;
@Entity
@Data
@Table(name = "ATTENDANCE")
public class AttendanceEntity {

@Id
@GeneratedValue(strategy = GenerationType.SEQUENCE)
 private  Long id;

@NotNull
 private  String status;

@NotNull
 private  String studentUsername;


}