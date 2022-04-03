package com.bau.graduateprojects.qrstudentsattendance.repositories.student;
 import com.bau.graduateprojects.qrstudentsattendance.entities.StudentEntity;
import java.util.List;
public interface StudentRepository {


public boolean existById(Long studentId)
;

public void removeById(Long id)
;

public StudentEntity getById(Long id)
;

public StudentEntity getByUsername(String username)
;

public StudentEntity insert(StudentEntity studentEntity)
;

public StudentEntity update(StudentEntity studentEntity)
;

public List<StudentEntity> list()
;

public Long getCount()
;

}