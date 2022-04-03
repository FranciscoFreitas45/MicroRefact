package com.bau.graduateprojects.qrstudentsattendance.repositories.studentCourse;
 import com.bau.graduateprojects.qrstudentsattendance.entities.StudentCourseEntity;
import java.util.List;
public interface StudentCourseRepository {


public boolean exist(Long studentId,Long courseId)
;

public StudentCourseEntity getByStudentIdAndCourseId(Long sId,Long cId)
;

public List<StudentCourseEntity> getAllCoursesByStudentId(Long sId)
;

public List<StudentCourseEntity> getAllStudentsByCourseId(Long cId)
;

public void removeById(Long id)
;

public StudentCourseEntity insert(StudentCourseEntity entity)
;

}