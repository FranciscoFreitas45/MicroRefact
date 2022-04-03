package com.bau.graduateprojects.qrstudentsattendance.repositories.lecture;
 import com.bau.graduateprojects.qrstudentsattendance.entities.LectureEntity;
import java.util.List;
public interface LectureRepository {


public boolean existById(Long lectureId)
;

public void removeById(Long id)
;

public LectureEntity getById(Long id)
;

public LectureEntity insert(LectureEntity lectureEntity)
;

public List<LectureEntity> list()
;

}