package com.ukefu.webim.service.repository;
 import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ukefu.webim.web.model.EkmAccess;
public interface EkmAccessRepository extends JpaRepository<EkmAccess, String>{


public List<EkmAccess> findByKnowledgeidAndOrgi(String knowledgeid,String orgi)
;

public Page<EkmAccess> findByDatastatusAndOrgi(boolean datastatus,String orgi,Pageable paramPageable)
;

public Page<EkmAccess> findByDatastatusAndKnowledgeowerAndOrgi(boolean datastatus,String knowledgeower,String orgi,Pageable paramPageable)
;

public EkmAccess findByIdAndOrgi(String id,String orgi)
;

public Page<EkmAccess> findByDatastatusAndCreaterAndOrgi(boolean datastatus,String creater,String orgi,Pageable paramPageable)
;

}