package com.bau.graduateprojects.qrstudentsattendance.repositories.teacher;
 import com.bau.graduateprojects.qrstudentsattendance.entities.TeacherEntity;
import java.util.List;
public interface TeacherRepository {


public boolean existById(Long teacherId)
;

public void removeById(Long id)
;

public TeacherEntity getById(Long id)
;

public TeacherEntity insert(TeacherEntity teacherEntity)
;

public TeacherEntity update(TeacherEntity teacherEntity)
;

public TeacherEntity getByUsername(String username)
;

public List<TeacherEntity> list()
;

public TeacherEntity login(String username,String password)
;

public Long getCount()
;

}