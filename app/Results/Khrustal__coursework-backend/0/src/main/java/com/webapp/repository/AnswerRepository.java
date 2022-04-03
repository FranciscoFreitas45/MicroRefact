package com.webapp.repository;
 import com.webapp.models.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long>{


}