package com.app.Interface;
public interface SubjectDivCompositService {

   public void deleteByDivId(int subId,int divId);
   public List<Subject> findByDivId(int divId);
   public void create(SubjectDivComposit subDivComp);
   public String findSubjectName(int subDivCompId);
}