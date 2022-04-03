package com.bau.graduateprojects.qrstudentsattendance.repositories.lectureCourse;
 import com.bau.graduateprojects.qrstudentsattendance.entities.LectureCourseEntity;
import com.bau.graduateprojects.qrstudentsattendance.exception.ResourceNotFoundException;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public class LectureCourseRepositoryImpl implements LectureCourseRepository{

 private  SpringJpaLectureCourseRepository jpaLectureCourseRepository;

public LectureCourseRepositoryImpl(SpringJpaLectureCourseRepository jpaLectureCourseRepository) {
    this.jpaLectureCourseRepository = jpaLectureCourseRepository;
}
@Override
public boolean exist(Long lectureId,Long courseId){
    return jpaLectureCourseRepository.existsLectureCourseEntityByLectureIdAndCourseId(lectureId, courseId);
}


@Override
public LectureCourseEntity getByLectureIdAndCourseId(Long lId,Long cId){
    return jpaLectureCourseRepository.findLectureCourseEntityByLectureIdAndCourseId(lId, cId).orElseThrow(() -> new ResourceNotFoundException("Lecture or Course not found with lId = " + lId + " & cId = " + cId));
}


@Override
public void removeById(Long id){
    jpaLectureCourseRepository.deleteById(id);
}


@Override
public LectureCourseEntity insert(LectureCourseEntity entity){
    return jpaLectureCourseRepository.save(entity);
}


@Override
public List<LectureCourseEntity> getAllLecturesByCourseId(Long cId){
    return jpaLectureCourseRepository.findAllByCourseId(cId);
}


}