package com.ukefu.webim.service.repository;
 import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ukefu.webim.web.model.QualityTemplate;
public interface QualityTemplateRepository extends JpaRepository<QualityTemplate, String>{


public List<QualityTemplate> findByTypeAndStatusAndOrgi(String type,String status,String orgi)
;

public List<QualityTemplate> findByOrgi(String orgi)
;

public QualityTemplate findByNameAndOrgi(String name,String orgi)
;

public QualityTemplate findByIdAndOrgi(String id,String orgi)
;

}