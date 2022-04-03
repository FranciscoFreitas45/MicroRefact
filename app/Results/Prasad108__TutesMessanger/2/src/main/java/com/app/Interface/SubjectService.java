package com.app.Interface;
public interface SubjectService {

   public Subject find(int id);
   public List<Subject> getallOfInstitute(int instituteId);
   public void deleteFromInstitute(int subId);
   public void create(Subject subject);
   public void update(Subject subject);
}