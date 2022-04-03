package com.bau.graduateprojects.qrstudentsattendance.servicies.student;
 import com.bau.graduateprojects.qrstudentsattendance.entities.StudentEntity;
import com.bau.graduateprojects.qrstudentsattendance.repositories.student.StudentRepository;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class StudentServiceImp implements StudentService{

 private  StudentRepository studentRepository;

public StudentServiceImp(StudentRepository studentRepository) {
    this.studentRepository = studentRepository;
}
@Override
public void removeById(Long id){
    studentRepository.removeById(id);
}


@Override
public StudentEntity getById(Long id){
    return studentRepository.getById(id);
}


@Override
public StudentEntity getByUsername(String username){
    return studentRepository.getByUsername(username);
}


@Override
public StudentEntity insert(StudentEntity studentEntity){
    return studentRepository.insert(studentEntity);
}


@Override
public StudentEntity update(StudentEntity studentEntity){
    return studentRepository.update(studentEntity);
}


@Override
public List<StudentEntity> list(){
    return studentRepository.list();
}


@Override
public Long getCount(){
    return studentRepository.getCount();
}


}