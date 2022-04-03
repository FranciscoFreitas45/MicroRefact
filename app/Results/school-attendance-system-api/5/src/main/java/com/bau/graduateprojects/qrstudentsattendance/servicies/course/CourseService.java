package com.bau.graduateprojects.qrstudentsattendance.servicies.course;
 import com.bau.graduateprojects.qrstudentsattendance.entities.CourseEntity;
import java.util.List;
public interface CourseService {


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