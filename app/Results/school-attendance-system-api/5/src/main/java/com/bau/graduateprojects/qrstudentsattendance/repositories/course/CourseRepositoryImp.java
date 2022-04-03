package com.bau.graduateprojects.qrstudentsattendance.repositories.course;
 import com.bau.graduateprojects.qrstudentsattendance.entities.CourseEntity;
import com.bau.graduateprojects.qrstudentsattendance.exception.ResourceNotFoundException;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public class CourseRepositoryImp implements CourseRepository{

 private  SpringJpaCourseRepository jpaCourseRepository;

public CourseRepositoryImp(SpringJpaCourseRepository jpaCourseRepository) {
    this.jpaCourseRepository = jpaCourseRepository;
}
@Override
public boolean existById(Long courseId){
    return jpaCourseRepository.existsById(courseId);
}


@Override
public void removeById(Long id){
    jpaCourseRepository.deleteById(id);
}


@Override
public CourseEntity getById(Long id){
    return jpaCourseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("course not found with id = " + id));
}


@Override
public CourseEntity insert(CourseEntity courseEntity){
    return jpaCourseRepository.save(courseEntity);
}


@Override
public CourseEntity update(CourseEntity courseEntity){
    return jpaCourseRepository.save(courseEntity);
}


@Override
public List<CourseEntity> list(){
    return jpaCourseRepository.findAll();
}


@Override
public Long getCount(){
    return jpaCourseRepository.count();
}


}