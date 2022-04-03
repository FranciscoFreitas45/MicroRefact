package com.bau.graduateprojects.qrstudentsattendance.servicies.attendance;
 import com.bau.graduateprojects.qrstudentsattendance.entities.AttendanceEntity;
import com.bau.graduateprojects.qrstudentsattendance.repositories.attendance.AttendanceRepository;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class AttendanceServiceImpl implements AttendanceService{

 private  AttendanceRepository attendanceRepository;

public AttendanceServiceImpl(AttendanceRepository attendanceRepository) {
    this.attendanceRepository = attendanceRepository;
}
@Override
public AttendanceEntity getAttendanceById(Long id){
    return attendanceRepository.getAttendanceById(id);
}


@Override
public AttendanceEntity insert(AttendanceEntity entity){
    return attendanceRepository.insert(entity);
}


@Override
public List<AttendanceEntity> list(){
    return attendanceRepository.list();
}


}