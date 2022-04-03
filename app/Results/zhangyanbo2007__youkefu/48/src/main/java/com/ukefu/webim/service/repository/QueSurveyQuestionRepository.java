package com.ukefu.webim.service.repository;
 import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ukefu.webim.web.model.QueSurveyQuestion;
public interface QueSurveyQuestionRepository extends JpaRepository<QueSurveyQuestion, String>{


public QueSurveyQuestion findByOrgiAndId(String orgi,String id)
;

public List<QueSurveyQuestion> findByOrgi(String orgi)
;

public QueSurveyQuestion findById(String id)
;

public Page<QueSurveyQuestion> findByProcessidAndOrgi(String processid,String orgi,Pageable paramPageable)
;

public List<QueSurveyQuestion> findByOrgiAndProcessid(String orgi,String processid)
;

}