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
@Table(name = "exam_mode", catalog = "tutesmessanger")
public class ExamMode {

 private  Integer id;

 private  String modeName;

 private  Set<Exam> exams;

public ExamMode() {
}public ExamMode(String modeName, Set<Exam> exams) {
    this.modeName = modeName;
    this.exams = exams;
}
public void setExams(Set<Exam> exams){
    this.exams = exams;
}


@Column(name = "Mode_name", length = 300)
public String getModeName(){
    return this.modeName;
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


@OneToMany(fetch = FetchType.LAZY, mappedBy = "examMode")
public Set<Exam> getExams(){
    return this.exams;
}


public void setModeName(String modeName){
    this.modeName = modeName;
}


}