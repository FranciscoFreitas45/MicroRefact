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
@Table(name = "exam_type", catalog = "tutesmessanger")
public class ExamType {

 private  Integer id;

 private  String typeName;

 private  Set<Exam> exams;

public ExamType() {
}public ExamType(String typeName, Set<Exam> exams) {
    this.typeName = typeName;
    this.exams = exams;
}
public void setExams(Set<Exam> exams){
    this.exams = exams;
}


public void setTypeName(String typeName){
    this.typeName = typeName;
}


public void setId(Integer id){
    this.id = id;
}


@Id
@GeneratedValue(strategy = IDENTITY)
@Column(name = "id", unique = true, nullable = false)
public Integer getId(){
    return this.id;
}


@OneToMany(fetch = FetchType.LAZY, mappedBy = "examType")
public Set<Exam> getExams(){
    return this.exams;
}


@Column(name = "Type_name", length = 300)
public String getTypeName(){
    return this.typeName;
}


}