package com.app.Interface;
public interface ResultService {

   public String smsSubjectStudentResult(int ExamId,int SubdivId);
   public int updateResult(List<HashMap<String,String>> StudResultList);
}