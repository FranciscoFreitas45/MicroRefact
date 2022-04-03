package com.bau.graduateprojects.qrstudentsattendance.repositories.lectureCourse;
 import com.bau.graduateprojects.qrstudentsattendance.entities.LectureCourseEntity;
import java.util.List;
public interface LectureCourseRepository {


public boolean exist(Long lectureId,Long courseId)
;

public LectureCourseEntity getByLectureIdAndCourseId(Long lId,Long cId)
;

public void removeById(Long id)
;

public LectureCourseEntity insert(LectureCourseEntity entity)
;

public List<LectureCourseEntity> getAllLecturesByCourseId(Long cId)
;

}