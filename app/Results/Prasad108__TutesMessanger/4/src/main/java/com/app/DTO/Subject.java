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
public class Subject {

 private  Integer id;

 private  Institute institute;

 private  String name;

 private  String discription;

 private  Set<SubjectDivComposit> subjectDivComposits;

public Subject() {
}public Subject(Institute institute, String name, String discription, Set<SubjectDivComposit> subjectDivComposits) {
    this.institute = institute;
    this.name = name;
    this.discription = discription;
    this.subjectDivComposits = subjectDivComposits;
}public Subject(Institute institute, String name, String discription) {
    this.institute = institute;
    this.name = name;
    this.discription = discription;
}
public void setName(String name){
    this.name = name;
}


public void setDiscription(String discription){
    this.discription = discription;
}


@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "institute_id")
public Institute getInstitute(){
    return this.institute;
}


public void setInstitute(Institute institute){
    this.institute = institute;
}


@Column(name = "name", length = 100)
public String getName(){
    return this.name;
}


@Column(name = "discription", length = 500)
public String getDiscription(){
    return this.discription;
}


@OneToMany(fetch = FetchType.LAZY, mappedBy = "subject")
public Set<SubjectDivComposit> getSubjectDivComposits(){
    return this.subjectDivComposits;
}


public void setId(Integer id){
    this.id = id;
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


}