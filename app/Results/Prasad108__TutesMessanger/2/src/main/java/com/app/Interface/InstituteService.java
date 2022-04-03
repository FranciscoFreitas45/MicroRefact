package com.app.Interface;
public interface InstituteService {

   public Institute find(String name);
   public List<Teacher> getallPendingTeacherForApproval(Institute institute);
   public List<Student> getallPendingStudentForApproval(Institute institute);
   public List<Student> getallStudentWhoAreNotInAnyDivision(Institute institute);
   public String GetSubjectTree(int InstId);
   public String GetInstituteTree(int InstId);
   public String TreeStructureSujectsNotInExam(int InstId,int ExamId);
   public String TreeStructureSujectsOFExam(int InstId,int ExamId);
}