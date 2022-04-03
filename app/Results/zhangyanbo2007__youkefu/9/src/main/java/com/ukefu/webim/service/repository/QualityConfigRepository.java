package com.ukefu.webim.service.repository;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ukefu.webim.web.model.QualityConfig;
public interface QualityConfigRepository extends JpaRepository<QualityConfig, String>{


public List<QualityConfig> findByOrgi(String orgi)
;

public QualityConfig findByIdAndOrgi(String id,String orgi)
;

}