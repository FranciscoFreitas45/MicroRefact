package com.ukefu.webim.service.repository;
 import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ukefu.webim.web.model.QueSurveyResult;
public interface QueSurveyResultRepository extends JpaRepository<QueSurveyResult, String>{


public QueSurveyResult findByEventidAndProcessidAndOrgi(String eventid,String processid,String orgi)
;

public QueSurveyResult findByIdAndOrgi(String id,String orgi)
;

public Page<QueSurveyResult> findAll(Specification<QueSurveyResult> spec,Pageable page)
;

}