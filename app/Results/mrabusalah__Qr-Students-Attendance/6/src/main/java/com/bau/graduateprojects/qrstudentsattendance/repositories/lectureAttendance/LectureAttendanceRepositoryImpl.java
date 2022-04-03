package com.bau.graduateprojects.qrstudentsattendance.repositories.lectureAttendance;
 import com.bau.graduateprojects.qrstudentsattendance.entities.AttendanceEntity;
import com.bau.graduateprojects.qrstudentsattendance.entities.LectureAttendanceEntity;
import com.bau.graduateprojects.qrstudentsattendance.repositories.attendance.AttendanceRepository;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
@Repository
public class LectureAttendanceRepositoryImpl implements LectureAttendanceRepository{

 private  SpringJpaLectureAttendanceRepository jpaLectureAttendanceRepository;

 private  AttendanceRepository attendanceRepository;

public LectureAttendanceRepositoryImpl(SpringJpaLectureAttendanceRepository jpaLectureAttendanceRepository, AttendanceRepository attendanceRepository) {
    this.jpaLectureAttendanceRepository = jpaLectureAttendanceRepository;
    this.attendanceRepository = attendanceRepository;
}
@Override
public LectureAttendanceEntity addAttendanceToLecture(LectureAttendanceEntity entity){
    return jpaLectureAttendanceRepository.save(entity);
}


@Override
public AttendanceEntity updateStatusAttendanceId(Long attId){
    AttendanceEntity attendanceById = attendanceRepository.getAttendanceById(attId);
    attendanceById.setStatus("YES");
    return attendanceRepository.update(attendanceById);
}


@Override
public List<AttendanceEntity> getAllAttendanceByLectureId(Long lId){
    List<AttendanceEntity> list = new ArrayList<>();
    jpaLectureAttendanceRepository.findAllByLectureId(lId).forEach(lectureAttendanceEntity -> {
        list.add(attendanceRepository.getAttendanceById(lectureAttendanceEntity.getAttendanceId()));
    });
    return list;
}


}