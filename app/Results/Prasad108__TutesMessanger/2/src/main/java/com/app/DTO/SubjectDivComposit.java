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
import javax.persistence.UniqueConstraint;
public class SubjectDivComposit {

 private  Integer id;

 private  Division division;

 private  Subject subject;

 private  Set<ExamSubjectStudentCompositTable> examSubjectStudentCompositTables;

public SubjectDivComposit() {
}public SubjectDivComposit(Division division, Subject subject) {
    this.division = division;
    this.subject = subject;
}public SubjectDivComposit(Division division, Subject subject, Set<ExamSubjectStudentCompositTable> examSubjectStudentCompositTables) {
    this.division = division;
    this.subject = subject;
    this.examSubjectStudentCompositTables = examSubjectStudentCompositTables;
}
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "subject_id", nullable = false)
public Subject getSubject(){
    return this.subject;
}


@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "Div_id", nullable = false)
public Division getDivision(){
    return this.division;
}


@Id
@GeneratedValue(strategy = IDENTITY)
@Column(name = "id", unique = true, nullable = false)
public Integer getId(){
    return this.id;
}


@OneToMany(fetch = FetchType.LAZY, mappedBy = "subjectDivComposit")
public Set<ExamSubjectStudentCompositTable> getExamSubjectStudentCompositTables(){
    return this.examSubjectStudentCompositTables;
}


}