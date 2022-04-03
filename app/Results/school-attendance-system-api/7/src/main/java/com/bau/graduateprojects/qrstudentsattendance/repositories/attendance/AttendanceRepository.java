package com.bau.graduateprojects.qrstudentsattendance.repositories.attendance;
 import com.bau.graduateprojects.qrstudentsattendance.entities.AttendanceEntity;
import java.util.List;
public interface AttendanceRepository {


public AttendanceEntity getAttendanceById(Long id)
;

public AttendanceEntity insert(AttendanceEntity entity)
;

public AttendanceEntity update(AttendanceEntity attendanceById)
;

public List<AttendanceEntity> list()
;

}