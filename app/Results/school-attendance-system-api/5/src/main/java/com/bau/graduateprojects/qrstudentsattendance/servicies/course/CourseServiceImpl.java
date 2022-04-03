package com.bau.graduateprojects.qrstudentsattendance.servicies.course;
 import com.bau.graduateprojects.qrstudentsattendance.entities.CourseEntity;
import com.bau.graduateprojects.qrstudentsattendance.repositories.course.CourseRepository;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class CourseServiceImpl implements CourseService{

 private  CourseRepository courseRepository;

public CourseServiceImpl(CourseRepository courseRepository) {
    this.courseRepository = courseRepository;
}
@Override
public void removeById(Long id){
    courseRepository.removeById(id);
}


@Override
public CourseEntity getById(Long id){
    return courseRepository.getById(id);
}


@Override
public CourseEntity insert(CourseEntity courseEntity){
    return courseRepository.insert(courseEntity);
}


@Override
public CourseEntity update(CourseEntity courseEntity){
    return courseRepository.update(courseEntity);
}


@Override
public List<CourseEntity> list(){
    return courseRepository.list();
}


@Override
public Long getCount(){
    return courseRepository.getCount();
}


}