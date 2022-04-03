package com.ukefu.webim.service.repository;
 import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ukefu.webim.web.model.QueSurveyProcess;
public interface QueSurveyProcessRepository extends JpaRepository<QueSurveyProcess, String>{


public List<QueSurveyProcess> findByOrgi(String orgi)
;

public QueSurveyProcess findByIdAndOrgi(String id,String orgi)
;

}