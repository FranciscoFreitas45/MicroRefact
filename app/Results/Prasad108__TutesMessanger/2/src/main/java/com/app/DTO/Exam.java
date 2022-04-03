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
public class Exam {

 private  Integer id;

 private  ExamMode examMode;

 private  ExamType examType;

 private  Institute institute;

 private  String discription;

 private  Integer outOf;

 private  Integer passingMarks;

 private  Byte regular;

 private  Set<ExamSubjectStudentCompositTable> examSubjectStudentCompositTables;

public Exam() {
}public Exam(ExamMode examMode, ExamType examType, Institute institute, String discription, Integer outOf, Integer passingMarks, Byte regular, Set<ExamSubjectStudentCompositTable> examSubjectStudentCompositTables) {
    this.examMode = examMode;
    this.examType = examType;
    this.institute = institute;
    this.discription = discription;
    this.outOf = outOf;
    this.passingMarks = passingMarks;
    this.regular = regular;
    this.examSubjectStudentCompositTables = examSubjectStudentCompositTables;
}
public void setExamSubjectStudentCompositTables(Set<ExamSubjectStudentCompositTable> examSubjectStudentCompositTables){
    this.examSubjectStudentCompositTables = examSubjectStudentCompositTables;
}


public void setDiscription(String discription){
    this.discription = discription;
}


@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "insitute_id")
public Institute getInstitute(){
    return this.institute;
}


public void setInstitute(Institute institute){
    this.institute = institute;
}


@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "exam_type_id")
public ExamType getExamType(){
    return this.examType;
}


@Column(name = "regular")
public Byte getRegular(){
    return this.regular;
}


@Id
@GeneratedValue(strategy = IDENTITY)
@Column(name = "id", unique = true, nullable = false)
public Integer getId(){
    return this.id;
}


@OneToMany(fetch = FetchType.LAZY, mappedBy = "exam")
public Set<ExamSubjectStudentCompositTable> getExamSubjectStudentCompositTables(){
    return this.examSubjectStudentCompositTables;
}


@Column(name = "passing_marks")
public Integer getPassingMarks(){
    return this.passingMarks;
}


@Column(name = "discription", length = 500)
public String getDiscription(){
    return this.discription;
}


public void setExamType(ExamType examType){
    this.examType = examType;
}


public void setId(Integer id){
    this.id = id;
}


public void setPassingMarks(Integer passingMarks){
    this.passingMarks = passingMarks;
}


public void setRegular(Byte regular){
    this.regular = regular;
}


public void setExamMode(ExamMode examMode){
    this.examMode = examMode;
}


public void setOutOf(Integer outOf){
    this.outOf = outOf;
}


@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "exam_mode_id")
public ExamMode getExamMode(){
    return this.examMode;
}


@Column(name = "out_of")
public Integer getOutOf(){
    return this.outOf;
}


}