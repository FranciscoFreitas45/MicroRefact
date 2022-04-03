package com.app.DTO;
 import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
public class Permissions {

 private  Integer id;

 private  boolean authoriseStudent;

 private  boolean authoriseTeacher;

 private  boolean fillAttendance;

 private  boolean fillSchedule;

 private  boolean mailParent;

 private  boolean mailStudent;

 private  boolean mailTeacher;

 private  boolean msgParent;

 private  boolean msgStudent;

 private  boolean msgTeacher;

 private  boolean setExam;

 private  boolean updateResults;

 private  boolean alterInstituteStructure;

 private  Set<Teacher> teachers;

 private  Set<Teacher> teachers_1;

public Permissions() {
}public Permissions(boolean authoriseStudent, boolean authoriseTeacher, boolean fillAttendance, boolean fillSchedule, boolean mailParent, boolean mailStudent, boolean mailTeacher, boolean msgParent, boolean msgStudent, boolean msgTeacher, boolean setExam, boolean updateResults, boolean alterInstituteStructure) {
    this.authoriseStudent = authoriseStudent;
    this.authoriseTeacher = authoriseTeacher;
    this.fillAttendance = fillAttendance;
    this.fillSchedule = fillSchedule;
    this.mailParent = mailParent;
    this.mailStudent = mailStudent;
    this.mailTeacher = mailTeacher;
    this.msgParent = msgParent;
    this.msgStudent = msgStudent;
    this.msgTeacher = msgTeacher;
    this.setExam = setExam;
    this.updateResults = updateResults;
    this.alterInstituteStructure = alterInstituteStructure;
}public Permissions(boolean authoriseStudent, boolean authoriseTeacher, boolean fillAttendance, boolean fillSchedule, boolean mailParent, boolean mailStudent, boolean mailTeacher, boolean msgParent, boolean msgStudent, boolean msgTeacher, boolean setExam, boolean updateResults, boolean alterInstituteStructure, Set<Teacher> teachers, Set<Teacher> teachers_1) {
    this.authoriseStudent = authoriseStudent;
    this.authoriseTeacher = authoriseTeacher;
    this.fillAttendance = fillAttendance;
    this.fillSchedule = fillSchedule;
    this.mailParent = mailParent;
    this.mailStudent = mailStudent;
    this.mailTeacher = mailTeacher;
    this.msgParent = msgParent;
    this.msgStudent = msgStudent;
    this.msgTeacher = msgTeacher;
    this.setExam = setExam;
    this.updateResults = updateResults;
    this.alterInstituteStructure = alterInstituteStructure;
    this.teachers = teachers;
    this.teachers_1 = teachers_1;
}
@Id
@GeneratedValue(strategy = IDENTITY)
@Column(name = "id", unique = true, nullable = false)
public Integer getId(){
    return this.id;
}


@OneToMany(fetch = FetchType.LAZY, mappedBy = "permissions")
public Set<Teacher> getTeachers(){
    return this.teachers;
}


@OneToMany(fetch = FetchType.LAZY, mappedBy = "permissions")
public Set<Teacher> getTeachers_1(){
    return this.teachers_1;
}


}