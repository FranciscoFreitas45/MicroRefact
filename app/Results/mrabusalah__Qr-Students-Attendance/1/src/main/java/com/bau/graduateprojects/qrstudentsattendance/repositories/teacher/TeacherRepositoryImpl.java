package com.bau.graduateprojects.qrstudentsattendance.repositories.teacher;
 import com.bau.graduateprojects.qrstudentsattendance.entities.CourseEntity;
import com.bau.graduateprojects.qrstudentsattendance.entities.TeacherEntity;
import com.bau.graduateprojects.qrstudentsattendance.exception.DuplicatedUsernameException;
import com.bau.graduateprojects.qrstudentsattendance.exception.ResourceNotFoundException;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public class TeacherRepositoryImpl implements TeacherRepository{

 private  SpringJpaTeacherRepository jpaTeacherRepository;

public TeacherRepositoryImpl(SpringJpaTeacherRepository jpaTeacherRepository) {
    this.jpaTeacherRepository = jpaTeacherRepository;
}
@Override
public void removeById(Long id){
    jpaTeacherRepository.deleteById(id);
}


@Override
public TeacherEntity insert(TeacherEntity teacherEntity){
    String username = teacherEntity.getUsername();
    if (isUsernameExist(username)) {
        throw new DuplicatedUsernameException(username + " username is already taken");
    }
    return jpaTeacherRepository.save(teacherEntity);
}


@Override
public TeacherEntity update(TeacherEntity teacherEntity){
    if (!isExist(teacherEntity.getId())) {
        throw new ResourceNotFoundException("teacher not found wit id = " + teacherEntity.getId());
    }
    return jpaTeacherRepository.save(teacherEntity);
}


public boolean isUsernameExist(String username){
    return jpaTeacherRepository.existsByUsername(username);
}


@Override
public List<TeacherEntity> list(){
    return jpaTeacherRepository.findAll();
}


@Override
public TeacherEntity login(String username,String password){
    throwIfWrongUsername(username);
    throwIfWrongPassword(username, password);
    return getByUsername(username);
}


public void throwIfWrongUsername(String username){
    if (!isUsernameExist(username)) {
        throw new ResourceNotFoundException("wrong username :(");
    }
}


@Override
public boolean existById(Long teacherId){
    return jpaTeacherRepository.existsById(teacherId);
}


@Override
public TeacherEntity getById(Long id){
    return jpaTeacherRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("teacher not found with id = " + id));
}


public void throwIfWrongPassword(String username,String password){
    TeacherEntity teacherEntity = getByUsername(username);
    if (!teacherEntity.getPassword().equals(password)) {
        throw new ResourceNotFoundException("wrong password :(");
    }
}


@Override
public TeacherEntity getByUsername(String username){
    return jpaTeacherRepository.findTeacherEntityByUsername(username).orElseThrow(() -> new ResourceNotFoundException("teacher not found with username " + username));
}


public boolean isExist(Long id){
    return jpaTeacherRepository.existsById(id);
}


@Override
public Long getCount(){
    return jpaTeacherRepository.count();
}


}