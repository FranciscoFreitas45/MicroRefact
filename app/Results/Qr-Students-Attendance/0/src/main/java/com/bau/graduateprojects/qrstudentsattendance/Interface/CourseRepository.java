package com.bau.graduateprojects.qrstudentsattendance.Interface;
public interface CourseRepository {

   public boolean existById(Long courseId);
   public CourseEntity getById(Long id);
   public CourseEntity update(CourseEntity courseEntity);
}