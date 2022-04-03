package com.ukefu.webim.service.repository;
 import org.springframework.data.jpa.repository.JpaRepository;
import com.ukefu.webim.web.model.QueSurveyResultPoint;
public interface QueSurveyResultPointRepository extends JpaRepository<QueSurveyResultPoint, String>{


public QueSurveyResultPoint findByIdAndOrgi(String id,String orgi)
;

}