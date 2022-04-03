package com.bau.graduateprojects.qrstudentsattendance.Interface;
public interface LectureRepository {

   public boolean existById(Long lectureId);
   public LectureEntity getById(Long id);
}