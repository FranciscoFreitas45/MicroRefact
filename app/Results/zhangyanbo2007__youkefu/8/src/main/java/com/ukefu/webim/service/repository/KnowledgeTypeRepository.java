package com.ukefu.webim.service.repository;
 import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ukefu.webim.web.model.KnowledgeType;
public interface KnowledgeTypeRepository extends JpaRepository<KnowledgeType, String>{


public int countByNameAndOrgiAndParentidNot(String name,String orgi,String parentid)
;

public List<KnowledgeType> findByNameAndOrgiAndTypeid(String name,String orgi,String typeid)
;

public KnowledgeType findByNameAndOrgiAndIdNot(String name,String orgi,String id)
;

public List<KnowledgeType> findByOrgi(String orgi)
;

public int countByNameAndOrgiAndIdNot(String name,String orgi,String id)
;

public KnowledgeType findByNameAndOrgi(String name,String orgi)
;

public List<KnowledgeType> findByNameAndOrgiAndTypeidAndIdNot(String name,String orgi,String typeid,String id)
;

public KnowledgeType findByIdAndOrgi(String id,String orgi)
;

public Page<KnowledgeType> findAll(Specification<KnowledgeType> spec,Pageable pageable)
;

public List<KnowledgeType> findByOrgiAndTypeid(String orgi,String typeid)
;

}