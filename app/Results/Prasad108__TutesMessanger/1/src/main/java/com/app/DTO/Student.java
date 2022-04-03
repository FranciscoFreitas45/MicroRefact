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

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://4";

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
@Column(name = "father", nullable = false, length = 50)
public String getFather(){
    return this.father;
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


@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "divid")
public Division getDivision(){
    return this.division;
}


@Column(name = "fName")
public String getFname(){
    return this.fname;
}


@Column(name = "email", length = 20)
public String getEmail(){
    return this.email;
}


public void setLogin(Login login){
    this.login = login;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setLogin"))

.queryParam("login",login)
;
restTemplate.put(builder.toUriString(),null);
}


public void setInstitute(Institute institute){
    this.institute = institute;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setInstitute"))

.queryParam("institute",institute)
;
restTemplate.put(builder.toUriString(),null);
}


}