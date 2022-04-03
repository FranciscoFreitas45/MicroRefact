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
@Table(name = "parent", catalog = "tutesmessanger")
public class Parent {

 private  Integer id;

 private  String fname;

 private  String lname;

 private  String contactno;

 private  String email;

 private  Set<Student> students;

 private  Set<Student> students_1;

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
public void setContactno(String contactno){
    this.contactno = contactno;
}


@Column(name = "lname", nullable = false, length = 50)
public String getLname(){
    return this.lname;
}


@OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
public Set<Student> getStudents(){
    return this.students;
}


public void setLname(String lname){
    this.lname = lname;
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


public void setFname(String fname){
    this.fname = fname;
}


public void setEmail(String email){
    this.email = email;
}


@Column(name = "fname", nullable = false, length = 50)
public String getFname(){
    return this.fname;
}


@OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
public Set<Student> getStudents_1(){
    return this.students_1;
}


public void setStudents_1(Set<Student> students_1){
    this.students_1 = students_1;
}


public void setId(Integer id){
    this.id = id;
}


@Column(name = "email", length = 50)
public String getEmail(){
    return this.email;
}


@Override
public String toString(){
    return "Parent [id=" + id + ", fname=" + fname + ", lname=" + lname + ", contactno=" + contactno + ", email=" + email + "]";
}


public void setStudents(Set<Student> students){
    this.students = students;
}


}