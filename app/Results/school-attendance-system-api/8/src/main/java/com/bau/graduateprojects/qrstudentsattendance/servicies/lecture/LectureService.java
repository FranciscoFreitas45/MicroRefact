package com.bau.graduateprojects.qrstudentsattendance.servicies.lecture;
 import com.bau.graduateprojects.qrstudentsattendance.entities.LectureEntity;
import java.util.List;
public interface LectureService {


public void removeById(Long id)
;

public LectureEntity getById(Long id)
;

public LectureEntity insert(LectureEntity lectureEntity)
;

public List<LectureEntity> list()
;

}