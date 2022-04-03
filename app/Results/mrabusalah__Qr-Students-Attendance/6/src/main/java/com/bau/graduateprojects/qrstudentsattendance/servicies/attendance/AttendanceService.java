package com.bau.graduateprojects.qrstudentsattendance.servicies.attendance;
 import com.bau.graduateprojects.qrstudentsattendance.entities.AttendanceEntity;
import java.util.List;
public interface AttendanceService {


public AttendanceEntity getAttendanceById(Long id)
;

public AttendanceEntity insert(AttendanceEntity entity)
;

public List<AttendanceEntity> list()
;

}