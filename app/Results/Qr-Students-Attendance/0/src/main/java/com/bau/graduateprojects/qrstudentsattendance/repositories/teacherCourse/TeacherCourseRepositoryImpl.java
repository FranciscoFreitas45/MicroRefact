package com.bau.graduateprojects.qrstudentsattendance.repositories.teacherCourse;
 import com.bau.graduateprojects.qrstudentsattendance.entities.TeacherCourseEntity;
import com.bau.graduateprojects.qrstudentsattendance.exception.ResourceNotFoundException;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public class TeacherCourseRepositoryImpl implements TeacherCourseRepository{

 private  SpringJpaTeacherCourseRepository jpaTeacherCourseRepository;

public TeacherCourseRepositoryImpl(SpringJpaTeacherCourseRepository jpaTeacherCourseRepository) {
    this.jpaTeacherCourseRepository = jpaTeacherCourseRepository;
}
@Override
public boolean exist(Long teacherId,Long courseId){
    return jpaTeacherCourseRepository.existsTeacherCourseEntityByTeacherIdAndCourseId(teacherId, courseId);
}


@Override
public TeacherCourseEntity getByTeacherIdAndCourseId(Long tId,Long cId){
    return jpaTeacherCourseRepository.findTeacherCourseEntityByTeacherIdAndCourseId(tId, cId).orElseThrow(() -> new ResourceNotFoundException("Course or Teacher not found with tId = " + tId + " & cId = " + cId));
}


@Override
public List<TeacherCourseEntity> getAllCoursesByTeacherId(Long tId){
    return jpaTeacherCourseRepository.findAllByTeacherId(tId);
}


@Override
public void removeById(Long id){
    jpaTeacherCourseRepository.deleteById(id);
}


@Override
public TeacherCourseEntity insert(TeacherCourseEntity entity){
    return jpaTeacherCourseRepository.save(entity);
}


}