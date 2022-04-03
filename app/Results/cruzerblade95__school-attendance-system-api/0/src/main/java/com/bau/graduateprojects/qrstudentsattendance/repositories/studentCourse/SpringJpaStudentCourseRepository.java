package com.bau.graduateprojects.qrstudentsattendance.repositories.studentCourse;
 import com.bau.graduateprojects.qrstudentsattendance.entities.StudentCourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
public interface SpringJpaStudentCourseRepository extends JpaRepository<StudentCourseEntity, Long>{


public Optional<StudentCourseEntity> findStudentCourseEntityByStudentIdAndCourseId(Long sId,Long cId)
;

public boolean existsStudentCourseEntityByCourseIdAndStudentId(Long cId,Long sId)
;

public List<StudentCourseEntity> findAllByCourseId(Long cId)
;

public List<StudentCourseEntity> findAllByStudentId(Long sId)
;

}