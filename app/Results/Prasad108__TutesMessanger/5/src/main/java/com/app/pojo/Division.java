package com.app.pojo;
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
@Entity
@Table(name = "division", catalog = "tutesmessanger")
public class Division {

 private  Integer id;

 private  Classes classes;

 private  String name;

 private  Set<Schedule> schedules;

 private  Set<SubjectDivComposit> subjectDivComposits;

 private  Set<Student> students;

 private  Set<Schedule> schedules_1;

public Division() {
}public Division(Classes classes, String name) {
    super();
    this.classes = classes;
    this.name = name;
}public Division(Classes classes, String name, Set<Schedule> schedules, Set<SubjectDivComposit> subjectDivComposits, Set<Student> students, Set<Schedule> schedules_1) {
    this.classes = classes;
    this.name = name;
    this.schedules = schedules;
    this.subjectDivComposits = subjectDivComposits;
    this.students = students;
    this.schedules_1 = schedules_1;
}
public void setName(String name){
    this.name = name;
}


@OneToMany(fetch = FetchType.LAZY, mappedBy = "division")
public Set<Student> getStudents(){
    return this.students;
}


@Column(name = "name", length = 50)
public String getName(){
    return this.name;
}


public void setSubjectDivComposits(Set<SubjectDivComposit> subjectDivComposits){
    this.subjectDivComposits = subjectDivComposits;
}


@Id
@GeneratedValue(strategy = IDENTITY)
@Column(name = "id", unique = true, nullable = false)
public Integer getId(){
    return this.id;
}


@OneToMany(fetch = FetchType.LAZY, mappedBy = "division")
public Set<Schedule> getSchedules_1(){
    return this.schedules_1;
}


public void setSchedules(Set<Schedule> schedules){
    this.schedules = schedules;
}


public void setSchedules_1(Set<Schedule> schedules_1){
    this.schedules_1 = schedules_1;
}


@OneToMany(fetch = FetchType.LAZY, mappedBy = "division")
public Set<SubjectDivComposit> getSubjectDivComposits(){
    return this.subjectDivComposits;
}


public void setId(Integer id){
    this.id = id;
}


@Override
public String toString(){
    return "Division [id=" + id + ", name=" + name + "]";
}


public void setClasses(Classes classes){
    this.classes = classes;
}


@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "classid")
public Classes getClasses(){
    return this.classes;
}


public void setStudents(Set<Student> students){
    this.students = students;
}


@OneToMany(fetch = FetchType.LAZY, mappedBy = "division")
public Set<Schedule> getSchedules(){
    return this.schedules;
}


}