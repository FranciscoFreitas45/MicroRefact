package com.bau.graduateprojects.qrstudentsattendance.repositories.lecture;
 import com.bau.graduateprojects.qrstudentsattendance.entities.LectureEntity;
import org.springframework.data.jpa.repository.JpaRepository;
public interface SpringJpaLectureRepository extends JpaRepository<LectureEntity, Long>{


public LectureEntity findLectureEntityByDate(String date)
;

public boolean existsLectureEntityByDate(String date)
;

}