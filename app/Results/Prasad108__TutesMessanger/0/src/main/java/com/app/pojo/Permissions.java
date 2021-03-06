package com.app.pojo;
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
@Entity
@Table(name = "permissions", catalog = "tutesmessanger")
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
@Column(name = "mail_teacher", nullable = false)
public boolean isMailTeacher(){
    return this.mailTeacher;
}


@Column(name = "alter_institute_structure", nullable = false)
public boolean isAlterInstituteStructure(){
    return this.alterInstituteStructure;
}


public void setMailTeacher(boolean mailTeacher){
    this.mailTeacher = mailTeacher;
}


public void setMsgStudent(boolean msgStudent){
    this.msgStudent = msgStudent;
}


public void setTeachers_1(Set<Teacher> teachers_1){
    this.teachers_1 = teachers_1;
}


@Column(name = "fill_attendance", nullable = false)
public boolean isFillAttendance(){
    return this.fillAttendance;
}


public void setMsgTeacher(boolean msgTeacher){
    this.msgTeacher = msgTeacher;
}


public void setTeachers(Set<Teacher> teachers){
    this.teachers = teachers;
}


@Id
@GeneratedValue(strategy = IDENTITY)
@Column(name = "id", unique = true, nullable = false)
public Integer getId(){
    return this.id;
}


@Column(name = "mail_student", nullable = false)
public boolean isMailStudent(){
    return this.mailStudent;
}


@Column(name = "fill_schedule", nullable = false)
public boolean isFillSchedule(){
    return this.fillSchedule;
}


public void setFillAttendance(boolean fillAttendance){
    this.fillAttendance = fillAttendance;
}


@Column(name = "authorise_teacher", nullable = false)
public boolean isAuthoriseTeacher(){
    return this.authoriseTeacher;
}


public void setFillSchedule(boolean fillSchedule){
    this.fillSchedule = fillSchedule;
}


public void setSetExam(boolean setExam){
    this.setExam = setExam;
}


public void setId(Integer id){
    this.id = id;
}


@Column(name = "msg_teacher", nullable = false)
public boolean isMsgTeacher(){
    return this.msgTeacher;
}


public void setUpdateResults(boolean updateResults){
    this.updateResults = updateResults;
}


@Column(name = "mail_parent", nullable = false)
public boolean isMailParent(){
    return this.mailParent;
}


@Column(name = "update_results", nullable = false)
public boolean isUpdateResults(){
    return this.updateResults;
}


@OneToMany(fetch = FetchType.LAZY, mappedBy = "permissions")
public Set<Teacher> getTeachers(){
    return this.teachers;
}


@OneToMany(fetch = FetchType.LAZY, mappedBy = "permissions")
public Set<Teacher> getTeachers_1(){
    return this.teachers_1;
}


public void setMailParent(boolean mailParent){
    this.mailParent = mailParent;
}


public void setAuthoriseStudent(boolean authoriseStudent){
    this.authoriseStudent = authoriseStudent;
}


public void setAuthoriseTeacher(boolean authoriseTeacher){
    this.authoriseTeacher = authoriseTeacher;
}


@Column(name = "set_exam", nullable = false)
public boolean isSetExam(){
    return this.setExam;
}


@Column(name = "authorise_student", nullable = false)
public boolean isAuthoriseStudent(){
    return this.authoriseStudent;
}


public void setMsgParent(boolean msgParent){
    this.msgParent = msgParent;
}


public void setMailStudent(boolean mailStudent){
    this.mailStudent = mailStudent;
}


@Column(name = "msg_parent", nullable = false)
public boolean isMsgParent(){
    return this.msgParent;
}


@Column(name = "msg_student", nullable = false)
public boolean isMsgStudent(){
    return this.msgStudent;
}


public void setAlterInstituteStructure(boolean alterInstituteStructure){
    this.alterInstituteStructure = alterInstituteStructure;
}


}