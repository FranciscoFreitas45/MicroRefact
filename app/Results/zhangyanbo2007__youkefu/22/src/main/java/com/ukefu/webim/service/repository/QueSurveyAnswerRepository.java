package com.ukefu.webim.service.repository;
 import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ukefu.webim.web.model.QueSurveyAnswer;
public interface QueSurveyAnswerRepository extends JpaRepository<QueSurveyAnswer, String>{


public List<QueSurveyAnswer> findByOrgiAndId(String orgi,String id)
;

public List<QueSurveyAnswer> findByOrgiAndQuestionid(String orgi,String questionid)
;

public List<QueSurveyAnswer> findByOrgi(String orgi)
;

public QueSurveyAnswer findById(String id)
;

public List<QueSurveyAnswer> findAll(Specification<QueSurveyAnswer> spec)
;

public List<QueSurveyAnswer> findByOrgiAndQuestionidAndAnstype(String orgi,String questionid,String anstype)
;

public List<QueSurveyAnswer> findByOrgiAndProcessid(String orgi,String processid)
;

public List<QueSurveyAnswer> findByOrgiAndProcessidAndAnstype(String orgi,String processid,String anstype)
;

}