package com.bau.graduateprojects.qrstudentsattendance.repositories.teacherCourse;
 import com.bau.graduateprojects.qrstudentsattendance.entities.TeacherCourseEntity;
import java.util.List;
public interface TeacherCourseRepository {


public boolean exist(Long teacherId,Long courseId)
;

public TeacherCourseEntity getByTeacherIdAndCourseId(Long tId,Long cId)
;

public List<TeacherCourseEntity> getAllCoursesByTeacherId(Long tId)
;

public void removeById(Long id)
;

public TeacherCourseEntity insert(TeacherCourseEntity entity)
;

}