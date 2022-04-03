package com.bau.graduateprojects.qrstudentsattendance.repositories.student;
 import com.bau.graduateprojects.qrstudentsattendance.entities.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface SpringJpaStudentRepository extends JpaRepository<StudentEntity, Long>{


public boolean existsStudentEntityByUsername(String username)
;

public Optional<StudentEntity> findByUsername(String username)
;

public void deleteStudentEntityByUsername(String username)
;

}