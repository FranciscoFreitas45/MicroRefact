package com.bau.graduateprojects.qrstudentsattendance.repositories.lectureAttendance;
 import com.bau.graduateprojects.qrstudentsattendance.entities.AttendanceEntity;
import com.bau.graduateprojects.qrstudentsattendance.entities.LectureAttendanceEntity;
import java.util.List;
public interface LectureAttendanceRepository {


public LectureAttendanceEntity addAttendanceToLecture(LectureAttendanceEntity entity)
;

public AttendanceEntity updateStatusAttendanceId(Long attId)
;

public List<AttendanceEntity> getAllAttendanceByLectureId(Long lId)
;

}