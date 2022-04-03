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
public class Division {

 private  Integer id;

 private  Classes classes;

 private  String name;

 private  Set<Schedule> schedules;

 private  Set<SubjectDivComposit> subjectDivComposits;

 private  Set<Student> students;

 private  Set<Schedule> schedules_1;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://5";

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
@OneToMany(fetch = FetchType.LAZY, mappedBy = "division")
public Set<Student> getStudents(){
    return this.students;
}


@Column(name = "name", length = 50)
public String getName(){
    return this.name;
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


public void setSchedules_1(Set<Schedule> schedules_1){
    this.schedules_1 = schedules_1;
}


@OneToMany(fetch = FetchType.LAZY, mappedBy = "division")
public Set<SubjectDivComposit> getSubjectDivComposits(){
    return this.subjectDivComposits;
}


@Override
public String toString(){
    return "Division [id=" + id + ", name=" + name + "]";
}


@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "classid")
public Classes getClasses(){
    return this.classes;
}


@OneToMany(fetch = FetchType.LAZY, mappedBy = "division")
public Set<Schedule> getSchedules(){
    return this.schedules;
}


public void setName(String name){
    this.name = name;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setName"))

.queryParam("name",name)
;
restTemplate.put(builder.toUriString(),null);
}


public void setClasses(Classes classes){
    this.classes = classes;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setClasses"))

.queryParam("classes",classes)
;
restTemplate.put(builder.toUriString(),null);
}


}