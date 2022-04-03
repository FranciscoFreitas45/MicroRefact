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
public class Parent {

 private  Integer id;

 private  String fname;

 private  String lname;

 private  String contactno;

 private  String email;

 private  Set<Student> students;

 private  Set<Student> students_1;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://6";

public Parent() {
}public Parent(String fname, String lname, String contactno) {
    this.fname = fname;
    this.lname = lname;
    this.contactno = contactno;
}public Parent(String fname, String lname, String contactno, String email, Set<Student> students, Set<Student> students_1) {
    this.fname = fname;
    this.lname = lname;
    this.contactno = contactno;
    this.email = email;
    this.students = students;
    this.students_1 = students_1;
}
@Column(name = "lname", nullable = false, length = 50)
public String getLname(){
    return this.lname;
}


@OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
public Set<Student> getStudents(){
    return this.students;
}


@Column(name = "contactno", nullable = false, length = 12)
public String getContactno(){
    return this.contactno;
}


@Id
@GeneratedValue(strategy = IDENTITY)
@Column(name = "id", unique = true, nullable = false)
public Integer getId(){
    return this.id;
}


@Column(name = "fname", nullable = false, length = 50)
public String getFname(){
    return this.fname;
}


@OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
public Set<Student> getStudents_1(){
    return this.students_1;
}


@Column(name = "email", length = 50)
public String getEmail(){
    return this.email;
}


public void setFname(String fname){
    this.fname = fname;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setFname"))

.queryParam("fname",fname)
;
restTemplate.put(builder.toUriString(),null);
}


public void setLname(String lname){
    this.lname = lname;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setLname"))

.queryParam("lname",lname)
;
restTemplate.put(builder.toUriString(),null);
}


}