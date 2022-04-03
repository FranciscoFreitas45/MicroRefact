package com.bau.graduateprojects.qrstudentsattendance.servicies.lecture;
 import com.bau.graduateprojects.qrstudentsattendance.entities.LectureEntity;
import com.bau.graduateprojects.qrstudentsattendance.repositories.lecture.LectureRepository;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class LectureServiceImpl implements LectureService{

 private  LectureRepository lectureRepository;

public LectureServiceImpl(LectureRepository lectureRepository) {
    this.lectureRepository = lectureRepository;
}
@Override
public void removeById(Long id){
    lectureRepository.removeById(id);
}


@Override
public LectureEntity getById(Long id){
    return lectureRepository.getById(id);
}


@Override
public LectureEntity insert(LectureEntity lectureEntity){
    return lectureRepository.insert(lectureEntity);
}


@Override
public List<LectureEntity> list(){
    return lectureRepository.list();
}


}