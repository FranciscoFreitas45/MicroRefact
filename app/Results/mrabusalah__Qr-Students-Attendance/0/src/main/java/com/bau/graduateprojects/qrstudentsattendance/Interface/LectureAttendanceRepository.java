package com.bau.graduateprojects.qrstudentsattendance.Interface;
public interface LectureAttendanceRepository {

   public List<AttendanceEntity> getAllAttendanceByLectureId(Long lId);
   public LectureAttendanceEntity addAttendanceToLecture(LectureAttendanceEntity entity);
   public AttendanceEntity updateStatusAttendanceId(Long attId);
}