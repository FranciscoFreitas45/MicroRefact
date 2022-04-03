package com.bau.graduateprojects.qrstudentsattendance.Interface;
public interface StudentCourseRepository {

   public boolean exist(Long studentId,Long courseId);
   public StudentCourseEntity insert(StudentCourseEntity entity);
   public StudentCourseEntity getByStudentIdAndCourseId(Long sId,Long cId);
   public void removeById(Long id);
   public List<StudentCourseEntity> getAllCoursesByStudentId(Long sId);
   public List<StudentCourseEntity> getAllStudentsByCourseId(Long cId);
}