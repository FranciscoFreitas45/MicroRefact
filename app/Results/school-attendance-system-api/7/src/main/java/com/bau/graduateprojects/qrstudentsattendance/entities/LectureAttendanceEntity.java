package com.bau.graduateprojects.qrstudentsattendance.entities;
 import lombok.Data;
import javax.persistence;
import javax.validation.constraints.NotNull;
@Entity
@Data
@Table(name = "LECTURE_ATTENDANCE")
public class LectureAttendanceEntity {

@Id
@GeneratedValue(strategy = GenerationType.SEQUENCE)
 private  Long id;

@NotNull
 private  Long attendanceId;

@NotNull
 private  Long lectureId;


}