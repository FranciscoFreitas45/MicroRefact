package com.bau.graduateprojects.qrstudentsattendance.Interface;
public interface TeacherRepository {

   public boolean existById(Long teacherId);
   public TeacherEntity getById(Long id);
}