package com.app.pojo;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name = "result", catalog = "tutesmessanger")
public class Result {

 private  Integer id;

 private  ExamSubjectStudentCompositTable examSubjectStudentCompositTable;

 private  Integer obtainedMarks;

 private  String remarks;

public Result() {
}public Result(ExamSubjectStudentCompositTable examSubjectStudentCompositTable, Integer obtainedMarks, String remarks) {
    this.examSubjectStudentCompositTable = examSubjectStudentCompositTable;
    this.obtainedMarks = obtainedMarks;
    this.remarks = remarks;
}
@Column(name = "obtained_marks")
public Integer getObtainedMarks(){
    return this.obtainedMarks;
}


public void setObtainedMarks(Integer obtainedMarks){
    this.obtainedMarks = obtainedMarks;
}


public void setExamSubjectStudentCompositTable(ExamSubjectStudentCompositTable examSubjectStudentCompositTable){
    this.examSubjectStudentCompositTable = examSubjectStudentCompositTable;
}


@Column(name = "remarks", length = 300)
public String getRemarks(){
    return this.remarks;
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


@Override
public String toString(){
    return "Result [id=" + id + ", obtainedMarks=" + obtainedMarks + ", remarks=" + remarks + "]";
}


@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "exam_subject_student_id")
public ExamSubjectStudentCompositTable getExamSubjectStudentCompositTable(){
    return this.examSubjectStudentCompositTable;
}


public void setRemarks(String remarks){
    this.remarks = remarks;
}


}