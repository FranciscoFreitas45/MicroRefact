package com.bau.graduateprojects.qrstudentsattendance.Interface;
public interface StudentRepository {

   public boolean existById(Long studentId);
   public StudentEntity getById(Long id);
}