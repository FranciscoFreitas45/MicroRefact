package com.ukefu.webim.service.repository;
 import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ukefu.webim.web.model.EkmKnowledgeType;
public interface EkmKnowledgeTypeRepository extends JpaRepository<EkmKnowledgeType, String>{


public List<EkmKnowledgeType> findByKnowbaseidAndParentidAndDeskshowAndOrgi(String knowbaseid,String parentid,boolean deskshow,String orgi)
;

public List<EkmKnowledgeType> findByKnowbaseidAndDeskshowAndOrgi(String knowbaseid,boolean deskshow,String orgi)
;

public List<EkmKnowledgeType> findByKnowbaseidAndParentidAndOrgi(String knowbaseid,String parentid,String orgi)
;

public List<EkmKnowledgeType> findByKnowbaseidAndParentidAndNavshowAndOrgi(String knowbaseid,String parentid,boolean navshow,String orgi)
;

public List<EkmKnowledgeType> findByOrgi(String orgi)
;

public List<EkmKnowledgeType> findByKnowbaseidAndOrgi(String knowbaseid,String orgi)
;

public List<EkmKnowledgeType> findByKnowbaseidAndNavshowAndOrgi(String knowbaseid,boolean navshow,String orgi)
;

public List<EkmKnowledgeType> findByParentidAndOrgi(String parentid,String orgi)
;

public EkmKnowledgeType findByNameAndOrgi(String name,String orgi)
;

public EkmKnowledgeType findByIdAndOrgi(String id,String orgi)
;

public Page<EkmKnowledgeType> findAll(Specification<EkmKnowledgeType> spec,Pageable page)
;

public List<EkmKnowledgeType> findByAuditerAndOrgi(String auditer,String orgi)
;

}