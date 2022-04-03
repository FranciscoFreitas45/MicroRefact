package com.ukefu.webim.service.repository;
 import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ukefu.webim.web.model.EkmShare;
public interface EkmShareRepository extends JpaRepository<EkmShare, String>{


public Page<EkmShare> findByDatastatusAndShareuserAndOrgi(boolean datastatus,String shareuser,String orgi,Pageable pageable)
;

public List<EkmShare> findByDatastatusAndKnowledgeidAndOrgi(boolean datastatus,String knowledgeid,String orgi)
;

public EkmShare findByIdAndOrgi(String id,String orgi)
;

public Page<EkmShare> findByDatastatusAndCreaterAndOrgi(boolean datastatus,String creater,String orgi,Pageable pageable)
;

}