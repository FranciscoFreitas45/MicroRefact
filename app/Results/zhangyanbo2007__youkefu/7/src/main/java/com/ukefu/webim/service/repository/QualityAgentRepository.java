package com.ukefu.webim.service.repository;
 import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ukefu.webim.web.model.QualityAgent;
public interface QualityAgentRepository extends JpaRepository<QualityAgent, String>{


public List<QualityAgent> findByOrgi(String orgi)
;

public List<QualityAgent> findByOrgiAndActid(String orgi,String actid)
;

public List<QualityAgent> findByNameAndOrgi(String name,String orgi)
;

public List<QualityAgent> findByActidAndOrgi(String actid,String orgi)
;

public QualityAgent findByIdAndOrgi(String id,String orgi)
;

}