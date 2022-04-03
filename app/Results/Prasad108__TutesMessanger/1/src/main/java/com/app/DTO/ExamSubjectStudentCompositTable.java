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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
public class ExamSubjectStudentCompositTable {

 private  Integer id;

 private  Exam exam;

 private  Student student;

 private  SubjectDivComposit subjectDivComposit;

 private  Date dateTime;

 private  Integer durationInMinutes;

 private  Integer outOfMarks;

 private  Integer passingMarks;

 private  Set<Result> results;

 private  Set<Result> results_1;

public ExamSubjectStudentCompositTable() {
}public ExamSubjectStudentCompositTable(Exam exam, Student student, SubjectDivComposit subjectDivComposit, Date dateTime, Integer durationInMinutes, Integer outOfMarks, Integer passingMarks, Set<Result> results, Set<Result> results_1) {
    this.exam = exam;
    this.student = student;
    this.subjectDivComposit = subjectDivComposit;
    this.dateTime = dateTime;
    this.durationInMinutes = durationInMinutes;
    this.outOfMarks = outOfMarks;
    this.passingMarks = passingMarks;
    this.results = results;
    this.results_1 = results_1;
}
public void setStudent(Student student){
    this.student = student;
}


@Temporal(TemporalType.DATE)
@Column(name = "date_time", length = 10)
public Date getDateTime(){
    return this.dateTime;
}


public void setExam(Exam exam){
    this.exam = exam;
}


@Id
@GeneratedValue(strategy = IDENTITY)
@Column(name = "id", unique = true, nullable = false)
public Integer getId(){
    return this.id;
}


@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "exam_id")
public Exam getExam(){
    return this.exam;
}


@OneToMany(fetch = FetchType.LAZY, mappedBy = "examSubjectStudentCompositTable")
public Set<Result> getResults_1(){
    return this.results_1;
}


public void setResults_1(Set<Result> results_1){
    this.results_1 = results_1;
}


@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "student_id")
public Student getStudent(){
    return this.student;
}


@Column(name = "passing_marks")
public Integer getPassingMarks(){
    return this.passingMarks;
}


public void setDateTime(Date dateTime){
    this.dateTime = dateTime;
}


@Column(name = "duration_in_minutes")
public Integer getDurationInMinutes(){
    return this.durationInMinutes;
}


public void setOutOfMarks(Integer outOfMarks){
    this.outOfMarks = outOfMarks;
}


@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "subject_div_id")
public SubjectDivComposit getSubjectDivComposit(){
    return this.subjectDivComposit;
}


@OneToMany(fetch = FetchType.LAZY, mappedBy = "examSubjectStudentCompositTable")
public Set<Result> getResults(){
    return this.results;
}


public void setId(Integer id){
    this.id = id;
}


public void setPassingMarks(Integer passingMarks){
    this.passingMarks = passingMarks;
}


@Column(name = "out_of_marks")
public Integer getOutOfMarks(){
    return this.outOfMarks;
}


@Override
public String toString(){
    return "ExamSubjectStudentCompositTable [id=" + id + ", dateTime=" + dateTime + ", durationInMinutes=" + durationInMinutes + ", outOfMarks=" + outOfMarks + ", passingMarks=" + passingMarks + "]";
}


public void setDurationInMinutes(Integer durationInMinutes){
    this.durationInMinutes = durationInMinutes;
}


public void setResults(Set<Result> results){
    this.results = results;
}


public void setSubjectDivComposit(SubjectDivComposit subjectDivComposit){
    this.subjectDivComposit = subjectDivComposit;
}


}