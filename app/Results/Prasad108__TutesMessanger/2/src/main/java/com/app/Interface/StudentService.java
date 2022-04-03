package com.app.Interface;
public interface StudentService {

   public Student find(int id);
   public List<Student> findByDivId(int id);
   public void deleteSelectedFromDiv(int id);
   public void SetDivisionId(int StudentId,int DiviID);
}