package com.ukefu.webim.service.repository;
 import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ukefu.webim.web.model.QualityResult;
public interface QualityResultRepository extends JpaRepository<QualityResult, String>{


public List<QualityResult> findByOrgi(String orgi)
;

public QualityResult findByDataidAndOrgi(String dataid,String orgi)
;

public QualityResult findByIdAndOrgi(String id,String orgi)
;

}