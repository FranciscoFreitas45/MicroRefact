package com.ukefu.webim.service.repository;
 import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ukefu.webim.web.model.EkmComments;
public interface EkmCommentsRepository extends JpaRepository<EkmComments, String>{


public List<EkmComments> findByKnowledgeidAndOrgi(String knowledgeid,String orgi)
;

public List<EkmComments> findByOrganAndDatastatusAndAndOrgi(String organ,boolean datastatus,String orgi)
;

public Page<EkmComments> findByOrgi(String orgi,Pageable pageable)
;

public List<EkmComments> findByCreaterAndDatastatusAndAndOrgi(String creater,boolean datastatus,String orgi)
;

public Page<EkmComments> findByDatastatusAndKnowledgeowerAndOrgi(boolean datastatus,String knowledgeower,String orgi,Pageable pageable)
;

public Page<EkmComments> findByDatastatusAndKnowledgeidAndOrgi(boolean datastatus,String knowledgeid,String orgi,Pageable pageable)
;

public EkmComments findByIdAndOrgi(String id,String orgi)
;

public Page<EkmComments> findByOrgiAndDatastatus(String orgi,boolean datastatus,Pageable pageable)
;

public Page<EkmComments> findByDatastatusAndCreaterAndOrgi(boolean datastatus,String creater,String orgi,Pageable pageable)
;

public List<EkmComments> findByKnowledgeidAndDatastatusAndOrgi(String knowledgeid,boolean datastatus,String orgi)
;

}