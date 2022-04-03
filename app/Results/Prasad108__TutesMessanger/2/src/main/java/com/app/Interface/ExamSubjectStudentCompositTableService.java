package com.app.Interface;
public interface ExamSubjectStudentCompositTableService {

   public List<Student> findByExamId(int examId,int subDivId);
   public ExamSubjectStudentCompositTable findByExamSubDivId(int examId,int subDivId);
   public void create(ExamSubjectStudentCompositTable examSubjectStudentComp);
   public void deletStudentFromExam(int StudId,int subDivId,int examId);
   public String examSubjectStudentResult(int ExamId,int SubdivId);
   public void deletSubjectFromExam(int ExamId,int SubdivId);
}