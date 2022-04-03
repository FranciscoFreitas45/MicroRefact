package com.app.DTO;
 import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
public class Institute {

 private  Integer id;

 private  String name;

 private  String address;

 private  String contactno;

 private  String email;

 private  Date subscriptionTill;

 private  Boolean subscritionEnable;

 private  Boolean enable;

 private  Set<Branch> branches;

 private  Set<Exam> exams;

 private  Set<Teacher> teachers;

 private  Set<Exam> exams_1;

 private  Set<Student> students;

 private  Set<Subject> subjects;

 private  Set<Subject> subjects_1;

 private  Set<Student> students_1;

 private  Set<Teacher> teachers_1;

 private  Set<Branch> branches_1;

public Institute() {
}public Institute(String name) {
    this.name = name;
}public Institute(String name, String address, String contactno, String email, Date subscriptionTill, Boolean subscritionEnable, Boolean enable, Set<Branch> branches, Set<Exam> exams, Set<Teacher> teachers, Set<Exam> exams_1, Set<Student> students, Set<Subject> subjects, Set<Subject> subjects_1, Set<Student> students_1, Set<Teacher> teachers_1, Set<Branch> branches_1) {
    this.name = name;
    this.address = address;
    this.contactno = contactno;
    this.email = email;
    this.subscriptionTill = subscriptionTill;
    this.subscritionEnable = subscritionEnable;
    this.enable = enable;
    this.branches = branches;
    this.exams = exams;
    this.teachers = teachers;
    this.exams_1 = exams_1;
    this.students = students;
    this.subjects = subjects;
    this.subjects_1 = subjects_1;
    this.students_1 = students_1;
    this.teachers_1 = teachers_1;
    this.branches_1 = branches_1;
}
@OneToMany(fetch = FetchType.LAZY, mappedBy = "institute")
public Set<Exam> getExams_1(){
    return this.exams_1;
}


@Column(name = "name", unique = true, nullable = false, length = 100)
public String getName(){
    return this.name;
}


@Column(name = "contactno", length = 15)
public String getContactno(){
    return this.contactno;
}


@OneToMany(fetch = FetchType.LAZY, mappedBy = "institute")
public Set<Subject> getSubjects(){
    return this.subjects;
}


@Id
@GeneratedValue(strategy = IDENTITY)
@Column(name = "id", unique = true, nullable = false)
public Integer getId(){
    return this.id;
}


@Column(name = "Subscrition_enable")
public Boolean getSubscritionEnable(){
    return this.subscritionEnable;
}


@OneToMany(fetch = FetchType.LAZY, mappedBy = "institute")
public Set<Branch> getBranches(){
    return this.branches;
}


@Column(name = "enable")
public Boolean getEnable(){
    return this.enable;
}


@OneToMany(fetch = FetchType.LAZY, mappedBy = "institute")
public Set<Branch> getBranches_1(){
    return this.branches_1;
}


@Column(name = "Address", length = 100)
public String getAddress(){
    return this.address;
}


@OneToMany(fetch = FetchType.LAZY, mappedBy = "institute")
public Set<Exam> getExams(){
    return this.exams;
}


@OneToMany(fetch = FetchType.LAZY, mappedBy = "institute")
public Set<Subject> getSubjects_1(){
    return this.subjects_1;
}


@OneToMany(fetch = FetchType.LAZY, mappedBy = "institute")
public Set<Student> getStudents(){
    return this.students;
}


@OneToMany(fetch = FetchType.LAZY, mappedBy = "institute")
public Set<Teacher> getTeachers(){
    return this.teachers;
}


@OneToMany(fetch = FetchType.LAZY, mappedBy = "institute")
public Set<Teacher> getTeachers_1(){
    return this.teachers_1;
}


@Temporal(TemporalType.DATE)
@Column(name = "Subscription_till", length = 10)
public Date getSubscriptionTill(){
    return this.subscriptionTill;
}


@OneToMany(fetch = FetchType.LAZY, mappedBy = "institute")
public Set<Student> getStudents_1(){
    return this.students_1;
}


@Column(name = "email", length = 50)
public String getEmail(){
    return this.email;
}


}