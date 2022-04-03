package com.app.DTO;
 import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
public class Student {

 private  Integer id;

 private  Division division;

 private  Institute institute;

 private  Login login;

 private  Parent parent;

 private  String fname;

 private  String father;

 private  String lname;

 private  String contactno;

 private  String email;

 private  Set<ExamSubjectStudentCompositTable> examSubjectStudentCompositTables;

public Student() {
}public Student(String father) {
    this.father = father;
}public Student(int id, String father) {
    this.id = id;
    this.father = father;
}public Student(int id, Institute institute, Login login, Parent parent, String fname, String lname, String contactno, String email, String father) {
    this.id = id;
    this.institute = institute;
    this.login = login;
    this.parent = parent;
    this.fname = fname;
    this.lname = lname;
    this.contactno = contactno;
    this.email = email;
    this.father = father;
}public Student(Division division, Institute institute, Login login, Parent parent, String fname, String father, String lname, String contactno, String email, Set<ExamSubjectStudentCompositTable> examSubjectStudentCompositTables) {
    this.division = division;
    this.institute = institute;
    this.login = login;
    this.parent = parent;
    this.fname = fname;
    this.father = father;
    this.lname = lname;
    this.contactno = contactno;
    this.email = email;
    this.examSubjectStudentCompositTables = examSubjectStudentCompositTables;
}
public void setDivision(Division division){
    this.division = division;
}


@Column(name = "father", nullable = false, length = 50)
public String getFather(){
    return this.father;
}


public void setContactno(String contactno){
    this.contactno = contactno;
}


public void setExamSubjectStudentCompositTables(Set<ExamSubjectStudentCompositTable> examSubjectStudentCompositTables){
    this.examSubjectStudentCompositTables = examSubjectStudentCompositTables;
}


@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "instid")
public Institute getInstitute(){
    return this.institute;
}


@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "parentid")
public Parent getParent(){
    return this.parent;
}


@Column(name = "lName")
public String getLname(){
    return this.lname;
}


public void setInstitute(Institute institute){
    this.institute = institute;
}


public void setLname(String lname){
    this.lname = lname;
}


@Column(name = "contactno", length = 20)
public String getContactno(){
    return this.contactno;
}


@Id
@GeneratedValue(strategy = IDENTITY)
@Column(name = "id", unique = true, nullable = false)
public Integer getId(){
    return this.id;
}


@OneToMany(fetch = FetchType.LAZY, mappedBy = "student")
public Set<ExamSubjectStudentCompositTable> getExamSubjectStudentCompositTables(){
    return this.examSubjectStudentCompositTables;
}


@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "loginid")
public Login getLogin(){
    return this.login;
}


public void setFname(String fname){
    this.fname = fname;
}


@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "divid")
public Division getDivision(){
    return this.division;
}


public void setEmail(String email){
    this.email = email;
}


@Column(name = "fName")
public String getFname(){
    return this.fname;
}


public void setLogin(Login login){
    this.login = login;
}


public void setId(Integer id){
    this.id = id;
}


public void setFather(String father){
    this.father = father;
}


@Column(name = "email", length = 20)
public String getEmail(){
    return this.email;
}


@Override
public String toString(){
    return "Student [id=" + id + ", fname=" + fname + ", father=" + father + ", lname=" + lname + ", contactno=" + contactno + ", email=" + email + "]";
}


public void setParent(Parent parent){
    this.parent = parent;
}


}