package com.bau.graduateprojects.qrstudentsattendance.repositories.studentCourse;
 import com.bau.graduateprojects.qrstudentsattendance.entities.StudentCourseEntity;
import com.bau.graduateprojects.qrstudentsattendance.exception.ResourceNotFoundException;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public class StudentCourseRepositoryImpl implements StudentCourseRepository{

 private  SpringJpaStudentCourseRepository jpaStudentCourseRepository;

public StudentCourseRepositoryImpl(SpringJpaStudentCourseRepository studentCourseRepository) {
    this.jpaStudentCourseRepository = studentCourseRepository;
}
@Override
public boolean exist(Long studentId,Long courseId){
    return jpaStudentCourseRepository.existsStudentCourseEntityByCourseIdAndStudentId(studentId, courseId);
}


@Override
public StudentCourseEntity getByStudentIdAndCourseId(Long sId,Long cId){
    return jpaStudentCourseRepository.findStudentCourseEntityByStudentIdAndCourseId(sId, cId).orElseThrow(() -> new ResourceNotFoundException("Course or student not found with sId = " + sId + " & cId = " + cId));
}


@Override
public List<StudentCourseEntity> getAllCoursesByStudentId(Long sId){
    return jpaStudentCourseRepository.findAllByStudentId(sId);
}


@Override
public List<StudentCourseEntity> getAllStudentsByCourseId(Long cId){
    return jpaStudentCourseRepository.findAllByCourseId(cId);
}


@Override
public void removeById(Long id){
    jpaStudentCourseRepository.deleteById(id);
}


@Override
public StudentCourseEntity insert(StudentCourseEntity entity){
    return jpaStudentCourseRepository.save(entity);
}


}