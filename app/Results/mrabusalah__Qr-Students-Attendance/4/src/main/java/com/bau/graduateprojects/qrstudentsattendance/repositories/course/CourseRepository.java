package com.bau.graduateprojects.qrstudentsattendance.repositories.course;
 import com.bau.graduateprojects.qrstudentsattendance.entities.CourseEntity;
import java.util.List;
public interface CourseRepository {


public boolean existById(Long courseId)
;

public void removeById(Long id)
;

public CourseEntity getById(Long id)
;

public CourseEntity insert(CourseEntity courseEntity)
;

public CourseEntity update(CourseEntity courseEntity)
;

public List<CourseEntity> list()
;

public Long getCount()
;

}