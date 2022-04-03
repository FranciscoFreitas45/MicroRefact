package com.bau.graduateprojects.qrstudentsattendance.repositories.attendance;
 import com.bau.graduateprojects.qrstudentsattendance.entities.AttendanceEntity;
import com.bau.graduateprojects.qrstudentsattendance.exception.ResourceNotFoundException;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public class AttendanceRepositoryImpl implements AttendanceRepository{

 private  SpringJpaAttendanceRepository jpaAttendanceRepository;

public AttendanceRepositoryImpl(SpringJpaAttendanceRepository jpaAttendanceRepository) {
    this.jpaAttendanceRepository = jpaAttendanceRepository;
}
@Override
public AttendanceEntity getAttendanceById(Long id){
    return jpaAttendanceRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("attendance not found with id = " + id));
}


@Override
public AttendanceEntity insert(AttendanceEntity entity){
    return jpaAttendanceRepository.save(entity);
}


@Override
public AttendanceEntity update(AttendanceEntity attendanceById){
    return jpaAttendanceRepository.save(attendanceById);
}


@Override
public List<AttendanceEntity> list(){
    return jpaAttendanceRepository.findAll();
}


}