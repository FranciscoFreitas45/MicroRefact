package com.bau.graduateprojects.qrstudentsattendance.repositories.attendance;
 import com.bau.graduateprojects.qrstudentsattendance.entities.AttendanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
public interface SpringJpaAttendanceRepository extends JpaRepository<AttendanceEntity, Long>{


}