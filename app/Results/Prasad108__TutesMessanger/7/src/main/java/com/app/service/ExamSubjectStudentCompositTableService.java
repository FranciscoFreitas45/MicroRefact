package com.app.service;
 import java.util.List;
import com.app.pojo.ExamSubjectStudentCompositTable;
import com.app.pojo.Student;
import com.app.pojo.SubjectDivComposit;
public interface ExamSubjectStudentCompositTableService {


public String examSubjectStudentResult(int ExamId,int SubdivId)
;

public List<ExamSubjectStudentCompositTable> getall()
;

public ExamSubjectStudentCompositTable edit(int id)
;

public ExamSubjectStudentCompositTable find(int id)
;

public ExamSubjectStudentCompositTable findByExamSubDivId(int examId,int subDivId)
;

public void create(ExamSubjectStudentCompositTable examSubjectStudentComp)
;

public void update(ExamSubjectStudentCompositTable examSubjectStudentComp)
;

public void delet(int id)
;

public void deletStudentFromExam(int StudId,int subDivId,int examId)
;

public void deletSubjectFromExam(int ExamId,int SubdivId)
;

public List<Student> findByExamId(int examId,int subDivId)
;

}