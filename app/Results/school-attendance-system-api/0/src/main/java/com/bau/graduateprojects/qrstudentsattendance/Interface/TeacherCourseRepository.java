package com.bau.graduateprojects.qrstudentsattendance.Interface;
public interface TeacherCourseRepository {

   public boolean exist(Long teacherId,Long courseId);
   public TeacherCourseEntity insert(TeacherCourseEntity entity);
   public TeacherCourseEntity getByTeacherIdAndCourseId(Long tId,Long cId);
   public void removeById(Long id);
   public List<TeacherCourseEntity> getAllCoursesByTeacherId(Long tId);
}